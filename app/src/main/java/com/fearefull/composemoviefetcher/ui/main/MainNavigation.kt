package com.fearefull.composemoviefetcher.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.fearefull.composemoviefetcher.ui.MovieFetcherAppState
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
import com.fearefull.composemoviefetcher.ui.main.profile.ProfileNavigator
import com.fearefull.composemoviefetcher.ui.main.profile.ProfileScreen
import com.fearefull.composemoviefetcher.ui.main.profile.ProfileViewModel

sealed class RouteScreenMain(val route: String) {
    object Movie : RouteScreenMain("movie")
    object Celebrity : RouteScreenMain("celebrity")
    object Profile : RouteScreenMain("profile")

    companion object {
        val DEFAULT_TAB = Movie
    }
}

sealed class RouteScreenMainLeaf(val route: String) {
    fun createRoute(root: RouteScreenMain) = "${root.route}/$route"

    object Movie : RouteScreenMainLeaf("movie")
    object Celebrity : RouteScreenMainLeaf("celebrity")
    object Profile : RouteScreenMainLeaf("profile")

    object MovieDetails : RouteScreenMainLeaf("movieDetails/{movieId}") {
        fun createRoute(root: RouteScreenMain, movieId: Long): String {
            return "${root.route}/movieDetails/$movieId"
        }
    }

    object CelebrityDetails : RouteScreenMainLeaf("celebrityDetails/{celebrityId}") {
        fun createRoute(root: RouteScreenMain, celebrityId: Long): String {
            return "${root.route}/celebrityDetails/$celebrityId"
        }
    }
}

@Composable
internal fun MainNavigation(
    modifier: Modifier = Modifier,
    mainState: MainState,
    appState: MovieFetcherAppState
) {
    NavHost(
        navController = mainState.navController,
        startDestination = RouteScreenMain.DEFAULT_TAB.route,
        modifier = modifier
    ) {
        addMovieTopLevel(appState, mainState)
        addCelebrityTopLevel(appState, mainState)
        addProfileTopLevel(appState, mainState)
    }
}

private fun NavGraphBuilder.addMovieTopLevel(
    appState: MovieFetcherAppState,
    mainState: MainState
) {
    navigation(
        route = RouteScreenMain.Movie.route,
        startDestination = RouteScreenMainLeaf.Movie.createRoute(RouteScreenMain.Movie)
    ) {
        addMovie(mainState, RouteScreenMain.Movie)
        addMovieDetails(appState, RouteScreenMain.Movie)
    }
}

private fun NavGraphBuilder.addCelebrityTopLevel(
    appState: MovieFetcherAppState,
    mainState: MainState
) {
    navigation(
        route = RouteScreenMain.Celebrity.route,
        startDestination = RouteScreenMainLeaf.Celebrity.createRoute(RouteScreenMain.Celebrity)
    ) {
        addCelebrity(mainState, RouteScreenMain.Celebrity)
        addCelebrityDetails(appState, RouteScreenMain.Celebrity)
    }
}

private fun NavGraphBuilder.addProfileTopLevel(
    appState: MovieFetcherAppState,
    mainState: MainState
) {
    navigation(
        route = RouteScreenMain.Profile.route,
        startDestination = RouteScreenMainLeaf.Profile.createRoute(RouteScreenMain.Profile)
    ) {
        addProfile(appState, mainState, RouteScreenMain.Profile)
    }
}

private fun NavGraphBuilder.addMovie(
    mainState: MainState,
    root: RouteScreenMain
) {
    composable(RouteScreenMainLeaf.Movie.createRoute(root)) {
        val viewModel: MoviesViewModel = hiltViewModel()

        MoviesScreen(
            state = viewModel.viewState.value,
            effectFlow = viewModel.effect,
            onEventSent = { event -> viewModel.setEvent(event) },
            onNavigationSent = { navigationEffect ->
                when(navigationEffect) {
                    is MoviesNavigator.Effect.Navigation.ToMovieDetails -> {
                        mainState.navigateToMovieDetails(it, root, navigationEffect.movie.id)
                    }
                }
            }
        )
    }
}

private fun NavGraphBuilder.addCelebrity(
    mainState: MainState,
    root: RouteScreenMain
) {
    composable(RouteScreenMainLeaf.Celebrity.createRoute(root)) {
        val viewModel: CelebritiesViewModel = hiltViewModel()

        CelebritiesScreen(
            state = viewModel.viewState.value,
            effectFlow = viewModel.effect,
            onEventSent = { event -> viewModel.setEvent(event) },
            onNavigationSent = { navigationEffect ->
                when(navigationEffect) {
                    is CelebritiesNavigator.Effect.Navigation.ToCelebrityDetails -> {
                        mainState.navigateToCelebrityDetails(it, root, navigationEffect.celebrity.id)
                    }
                }
            }
        )
    }
}

private fun NavGraphBuilder.addProfile(
    appState: MovieFetcherAppState,
    mainState: MainState,
    root: RouteScreenMain
) {
    composable(RouteScreenMainLeaf.Profile.createRoute(root)) {
        val viewModel: ProfileViewModel = hiltViewModel()
        ProfileScreen(
            state = viewModel.viewState.value,
            effectFlow = viewModel.effect,
            onEventSent = { event -> viewModel.setEvent(event) },
            onNavigationSent = { navigationEffect ->
                when(navigationEffect) {
                    is ProfileNavigator.Effect.Navigation.ToAuth -> {
                        appState.navigateToAuth(it)
                    }
                }
            }
        )
    }
}

private fun NavGraphBuilder.addMovieDetails(
    appState: MovieFetcherAppState,
    root: RouteScreenMain
) {
    composable(
        RouteScreenMainLeaf.MovieDetails.createRoute(root),
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
    root: RouteScreenMain
) {
    composable(
        RouteScreenMainLeaf.CelebrityDetails.createRoute(root),
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