package com.fearefull.composemoviefetcher.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.fearefull.composemoviefetcher.ui.auth.sign_in.SignInNavigator
import com.fearefull.composemoviefetcher.ui.auth.sign_in.SignInScreen
import com.fearefull.composemoviefetcher.ui.auth.sign_in.SignInViewModel
import com.fearefull.composemoviefetcher.ui.auth.sign_up.SignUpNavigator
import com.fearefull.composemoviefetcher.ui.auth.sign_up.SignUpScreen
import com.fearefull.composemoviefetcher.ui.auth.sign_up.SignUpViewModel
import com.fearefull.composemoviefetcher.ui.main.celebrities.CelebritiesNavigator
import com.fearefull.composemoviefetcher.ui.main.celebrities.CelebritiesScreen
import com.fearefull.composemoviefetcher.ui.main.celebrities.CelebritiesViewModel
import com.fearefull.composemoviefetcher.ui.main.celebrity_details.CelebrityDetailsScreen
import com.fearefull.composemoviefetcher.ui.main.celebrity_details.CelebrityDetailsViewModel
import com.fearefull.composemoviefetcher.ui.main.movie_details.MovieDetailsScreen
import com.fearefull.composemoviefetcher.ui.main.movie_details.MovieDetailsViewModel
import com.fearefull.composemoviefetcher.ui.main.movies.MoviesNavigator
import com.fearefull.composemoviefetcher.ui.main.movies.MoviesScreen
import com.fearefull.composemoviefetcher.ui.main.movies.MoviesViewModel
import com.fearefull.composemoviefetcher.ui.main.profile.Profile
import com.fearefull.composemoviefetcher.ui.splash.SplashNavigator
import com.fearefull.composemoviefetcher.ui.splash.SplashScreen
import com.fearefull.composemoviefetcher.ui.splash.SplashViewModel

/**
 * Created by Aref Hosseini on ۱۶/۱۱/۲۰۲۱.
 */

sealed class RouteScreen(val route: String) {
    object Splash : RouteScreen("splash")
    object SignUp : RouteScreen("signUp")
    object SignIn : RouteScreen("signIn")

    // main tabs
    object Movie : RouteScreen("movie")
    object Celebrity : RouteScreen("celebrity")
    object Profile : RouteScreen("profile")

    companion object {
        fun hasRoute(route: String?): Boolean =
            route?.let {
                route.contains(Movie.route) || route.contains(Celebrity.route) || route.contains(Profile.route)
            } ?: run { false }
    }
}

sealed class RouteScreenMain(val route: String) {
    fun createRoute(root: RouteScreen) = "${root.route}/$route"

    object Movie : RouteScreenMain("movie")
    object Celebrity : RouteScreenMain("celebrity")
    object Profile : RouteScreenMain("profile")

    object MovieDetails : RouteScreenMain("movieDetails/{movieId}") {
        fun createRoute(root: RouteScreen, movieId: Long): String {
            return "${root.route}/movieDetails/$movieId"
        }
    }
    
    object CelebrityDetails : RouteScreenMain("celebrityDetails/{celebrityId}") {
        fun createRoute(root: RouteScreen, celebrityId: Long): String {
            return "${root.route}/celebrityDetails/$celebrityId"
        }
    }
}

@Composable
internal fun AppNavigation(
    modifier: Modifier = Modifier,
    appState: MovieFetcherAppState,
) {
    NavHost(
        navController = appState.navController,
        startDestination = RouteScreen.Splash.route,
        modifier = modifier
    ) {
        addSplash(appState)
        addSignUp(appState)
        addSignIn(appState)
        addMovieTopLevel(appState)
        addCelebrityTopLevel(appState)
        addProfileTopLevel(appState)
    }
}

private fun NavGraphBuilder.addSplash(
    appState: MovieFetcherAppState,
) {
    composable(route = RouteScreen.Splash.route) {
        val viewModel: SplashViewModel = hiltViewModel()

        SplashScreen(
            state = viewModel.viewState.value,
            effectFlow = viewModel.effect,
            onEventSent = { event -> viewModel.setEvent(event) },
            onNavigationSent = { navigationEffect ->
                when(navigationEffect) {
                    is SplashNavigator.Effect.Navigation.ToMain -> {
                        appState.navigateToMain(it)
                    }
                    is SplashNavigator.Effect.Navigation.ToAuth -> {
                        appState.navigateToSignIn(it)
                    }
                }
            }
        )

    }
}

private fun NavGraphBuilder.addSignUp(
    appState: MovieFetcherAppState,
) {
    composable(route = RouteScreen.SignUp.route) {
        val viewModel: SignUpViewModel = hiltViewModel()

        SignUpScreen(
            state = viewModel.viewState.value,
            effectFlow = viewModel.effect,
            onEventSent = { event -> viewModel.setEvent(event) },
            onNavigationSent = { navigationEffect ->
                when(navigationEffect) {
                    is SignUpNavigator.Effect.Navigation.ToMain ->
                        appState.navigateToMain(it)
                    is SignUpNavigator.Effect.Navigation.ToSignIn ->
                        appState.navigateToSignIn(it)
                }
            }
        )
    }
}

private fun NavGraphBuilder.addSignIn(
    appState: MovieFetcherAppState,
) {
    composable(route = RouteScreen.SignIn.route) {
        val viewModel: SignInViewModel = hiltViewModel()

        SignInScreen(
            state = viewModel.viewState.value,
            effectFlow = viewModel.effect,
            onEventSent = { event -> viewModel.setEvent(event) },
            onNavigationSent = { navigationEffect ->
                when(navigationEffect) {
                    is SignInNavigator.Effect.Navigation.ToMain ->
                        appState.navigateToMain(it)
                    is SignInNavigator.Effect.Navigation.ToSignUp ->
                        appState.navigateToSignUp(it)
                }
            }
        )
    }
}

private fun NavGraphBuilder.addMovieTopLevel(
    appState: MovieFetcherAppState) {
    navigation(
        route = RouteScreen.Movie.route,
        startDestination = RouteScreenMain.Movie.createRoute(RouteScreen.Movie)
    ) {
        addMovie(appState, RouteScreen.Movie)
        addMovieDetails(appState, RouteScreen.Movie)
    }
}

private fun NavGraphBuilder.addCelebrityTopLevel(
    appState: MovieFetcherAppState) {
    navigation(
        route = RouteScreen.Celebrity.route,
        startDestination = RouteScreenMain.Celebrity.createRoute(RouteScreen.Celebrity)
    ) {
        addCelebrity(appState, RouteScreen.Celebrity)
        addCelebrityDetails(appState, RouteScreen.Celebrity)
    }
}

private fun NavGraphBuilder.addProfileTopLevel(
    appState: MovieFetcherAppState) {
    navigation(
        route = RouteScreen.Profile.route,
        startDestination = RouteScreenMain.Profile.createRoute(RouteScreen.Profile)
    ) {
        addProfile(appState, RouteScreen.Profile)
    }
}

private fun NavGraphBuilder.addMovie(
    appState: MovieFetcherAppState,
    root: RouteScreen
) {
    composable(RouteScreenMain.Movie.createRoute(root)) {
        val viewModel: MoviesViewModel = hiltViewModel()

        MoviesScreen(
            state = viewModel.viewState.value,
            effectFlow = viewModel.effect,
            onEventSent = { event -> viewModel.setEvent(event) },
            onNavigationSent = { navigationEffect ->
                when(navigationEffect) {
                    is MoviesNavigator.Effect.Navigation.ToMovieDetails -> {
                        appState.navigateToMovieDetails(it, root, navigationEffect.movie.id)
                    }
                }
            }
        )
    }
}

private fun NavGraphBuilder.addCelebrity(
    appState: MovieFetcherAppState,
    root: RouteScreen
) {
    composable(RouteScreenMain.Celebrity.createRoute(root)) {
        val viewModel: CelebritiesViewModel = hiltViewModel()

        CelebritiesScreen(
            state = viewModel.viewState.value,
            effectFlow = viewModel.effect,
            onEventSent = { event -> viewModel.setEvent(event) },
            onNavigationSent = { navigationEffect ->
                when(navigationEffect) {
                    is CelebritiesNavigator.Effect.Navigation.ToCelebrityDetails -> {
                        appState.navigateToCelebrityDetails(it, root, navigationEffect.celebrity.id)
                    }
                }
            }
        )
    }
}

private fun NavGraphBuilder.addProfile(
    appState: MovieFetcherAppState,
    root: RouteScreen
) {
    composable(RouteScreenMain.Profile.createRoute(root)) {
        Profile()
    }
}

private fun NavGraphBuilder.addMovieDetails(
    appState: MovieFetcherAppState,
    root: RouteScreen
) {
    composable(
        RouteScreenMain.MovieDetails.createRoute(root),
        arguments = listOf(
            navArgument("movieId") { type = NavType.LongType }
        )
    ) {
        val viewModel: MovieDetailsViewModel = hiltViewModel()
        MovieDetailsScreen(
            state = viewModel.viewState.value,
            effectFlow = viewModel.effect,
            onEventSent = { event -> viewModel.setEvent(event) },
            onNavigationSent = { navigationEffect ->
                // TODO: Navigation
            }
        )
    }
}

private fun NavGraphBuilder.addCelebrityDetails(
    appState: MovieFetcherAppState,
    root: RouteScreen
) {
    composable(
        RouteScreenMain.CelebrityDetails.createRoute(root),
        arguments = listOf(
            navArgument("celebrityId") { type = NavType.LongType }
        )
    ) {
        val viewModel: CelebrityDetailsViewModel = hiltViewModel()
        CelebrityDetailsScreen(
            state = viewModel.viewState.value, 
            effectFlow = viewModel.effect, 
            onEventSent = { event -> viewModel.setEvent(event) }, 
            onNavigationSent = { navigationEffect ->
                // TODO: NAv 
            }
        )
    }
}