package com.fearefull.composemoviefetcher.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.fearefull.composemoviefetcher.ui.auth.Auth
import com.fearefull.composemoviefetcher.ui.main.celebrities.CelebritiesScreen
import com.fearefull.composemoviefetcher.ui.main.celebrities.CelebritiesViewModel
import com.fearefull.composemoviefetcher.ui.main.movies.MoviesScreen
import com.fearefull.composemoviefetcher.ui.main.movies.MoviesViewModel
import com.fearefull.composemoviefetcher.ui.main.profile.Profile
import com.fearefull.composemoviefetcher.ui.splash.Splash

/**
 * Created by Aref Hosseini on ۱۶/۱۱/۲۰۲۱.
 */

sealed class RouteScreen(val route: String) {
    object Splash : RouteScreen("splash")
    object Auth : RouteScreen("auth")

    // main tabs
    object Movie : RouteScreen("movie")
    object Celebrity : RouteScreen("celebrity")
    object Profile : RouteScreen("profile")

    companion object {
        fun hasRoute(route: String?): Boolean =
            route?.let {
                route.contains(Movie.route) || route.contains(Celebrity.route) || route.contains(Profile.route)
            } ?: kotlin.run { false }
    }
}

sealed class RouteScreenMain(val route: String) {
    object Movie : RouteScreenMain("movie")
    object Celebrity : RouteScreenMain("celebrity")
    object Profile : RouteScreenMain("profile")

    fun createRoute(root: RouteScreen) = "${root.route}/$route"
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
        addAuth(appState)
        addMovieTopLevel(appState)
        addCelebrityTopLevel(appState)
        addProfileTopLevel(appState)
    }
}

private fun NavGraphBuilder.addSplash(
    appState: MovieFetcherAppState,
) {
    composable(route = RouteScreen.Splash.route) {
        Splash(
            openMain = {
                appState.navigateToMain(it)
            }
        )
    }
}

private fun NavGraphBuilder.addAuth(
    appState: MovieFetcherAppState,
) {
    composable(route = RouteScreen.Auth.route) {
        Auth()
    }
}

private fun NavGraphBuilder.addMovieTopLevel(
    appState: MovieFetcherAppState) {
    navigation(
        route = RouteScreen.Movie.route,
        startDestination = RouteScreenMain.Movie.createRoute(RouteScreen.Movie)
    ) {
        addMovie(appState)
    }
}

private fun NavGraphBuilder.addCelebrityTopLevel(
    appState: MovieFetcherAppState) {
    navigation(
        route = RouteScreen.Celebrity.route,
        startDestination = RouteScreenMain.Celebrity.createRoute(RouteScreen.Celebrity)
    ) {
        addCelebrity(appState)
    }
}

private fun NavGraphBuilder.addProfileTopLevel(
    appState: MovieFetcherAppState) {
    navigation(
        route = RouteScreen.Profile.route,
        startDestination = RouteScreenMain.Profile.createRoute(RouteScreen.Profile)
    ) {
        addProfile(appState)
    }
}

private fun NavGraphBuilder.addMovie(
    appState: MovieFetcherAppState,
) {
    composable(RouteScreenMain.Movie.createRoute(RouteScreen.Movie)) {
        val viewModel: MoviesViewModel = hiltViewModel()

        MoviesScreen(
            state = viewModel.viewState.value,
            effectFlow = viewModel.effect,
            onEventSent = { event -> viewModel.setEvent(event) },
            onNavigationSent = { navigationEffect ->
                // TODO: Navigation
            }
        )
    }
}

private fun NavGraphBuilder.addCelebrity(
    appState: MovieFetcherAppState,
) {
    composable(RouteScreenMain.Celebrity.createRoute(RouteScreen.Celebrity)) {
        val viewModel: CelebritiesViewModel = hiltViewModel()

        CelebritiesScreen(
            state = viewModel.viewState.value,
            effectFlow = viewModel.effect,
            onEventSent = { event -> viewModel.setEvent(event) },
            onNavigationSent = { navigationEffect ->
                // TODO: Navigation
            }
        )
    }
}

private fun NavGraphBuilder.addProfile(
    appState: MovieFetcherAppState,
) {
    composable(RouteScreenMain.Profile.createRoute(RouteScreen.Profile)) {
        Profile()
    }
}