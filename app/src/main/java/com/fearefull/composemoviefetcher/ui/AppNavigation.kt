package com.fearefull.composemoviefetcher.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fearefull.composemoviefetcher.ui.auth.Auth
import com.fearefull.composemoviefetcher.ui.main.celebrity.Celebrity
import com.fearefull.composemoviefetcher.ui.main.movie.Movie
import com.fearefull.composemoviefetcher.ui.main.profile.Profile
import com.fearefull.composemoviefetcher.ui.splash.Splash

/**
 * Created by Aref Hosseini on ۱۶/۱۱/۲۰۲۱.
 */

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Auth : Screen("auth")

    // main tabs
    object Movie : Screen("movie")
    object Celebrity : Screen("celebrity")
    object Profile : Screen("profile")

    companion object {
        fun hasRoute(route: String?): Boolean =
            route?.let {
                route.contains(Movie.route) || route.contains(Celebrity.route) || route.contains(Profile.route)
            } ?: kotlin.run { false }
    }
}

sealed class MainScreen(val route: String) {
    object Movie : MainScreen("movie")
    object Celebrity : MainScreen("celebrity")
    object Profile : MainScreen("profile")

    fun createRoute(root: Screen) = "${root.route}/$route"
}

@Composable
internal fun AppNavigation(
    modifier: Modifier = Modifier,
    appState: MovieFetcherAppState,
) {
    NavHost(
        navController = appState.navController,
        startDestination = Screen.Splash.route,
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
    composable(route = Screen.Splash.route) {
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
    composable(route = Screen.Auth.route) {
        Auth()
    }
}

private fun NavGraphBuilder.addMovieTopLevel(
    appState: MovieFetcherAppState) {
    navigation(
        route = Screen.Movie.route,
        startDestination = MainScreen.Movie.createRoute(Screen.Movie)
    ) {
        addMovie(appState)
    }
}

private fun NavGraphBuilder.addCelebrityTopLevel(
    appState: MovieFetcherAppState) {
    navigation(
        route = Screen.Celebrity.route,
        startDestination = MainScreen.Celebrity.createRoute(Screen.Celebrity)
    ) {
        addCelebrity(appState)
    }
}

private fun NavGraphBuilder.addProfileTopLevel(
    appState: MovieFetcherAppState) {
    navigation(
        route = Screen.Profile.route,
        startDestination = MainScreen.Profile.createRoute(Screen.Profile)
    ) {
        addProfile(appState)
    }
}

private fun NavGraphBuilder.addMovie(
    appState: MovieFetcherAppState,
) {
    composable(MainScreen.Movie.createRoute(Screen.Movie)) {
        Movie()
    }
}

private fun NavGraphBuilder.addCelebrity(
    appState: MovieFetcherAppState,
) {
    composable(MainScreen.Celebrity.createRoute(Screen.Celebrity)) {
        Celebrity()
    }
}

private fun NavGraphBuilder.addProfile(
    appState: MovieFetcherAppState,
) {
    composable(MainScreen.Profile.createRoute(Screen.Profile)) {
        Profile()
    }
}