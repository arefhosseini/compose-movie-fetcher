package com.fearefull.composemoviefetcher.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fearefull.composemoviefetcher.ui.auth.Auth
import com.fearefull.composemoviefetcher.ui.main.home.Home
import com.fearefull.composemoviefetcher.ui.main.profile.Profile
import com.fearefull.composemoviefetcher.ui.splash.Splash

/**
 * Created by Aref Hosseini on ۱۶/۱۱/۲۰۲۱.
 */

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Auth : Screen("auth")

    // main tabs
    object Home : Screen("home")
    object Profile : Screen("profile")

    companion object {
        fun hasRoute(route: String?): Boolean =
            route?.let {
                route.contains(Home.route) || route.contains(Profile.route)
            } ?: kotlin.run { false }
    }
}

sealed class MainScreen(val route: String) {
    object Home : MainScreen("home")
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
        addHomeTopLevel(appState)
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

private fun NavGraphBuilder.addHomeTopLevel(
    appState: MovieFetcherAppState) {
    navigation(
        route = Screen.Home.route,
        startDestination = MainScreen.Home.createRoute(Screen.Home)
    ) {
        addHome(appState)
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

private fun NavGraphBuilder.addHome(
    appState: MovieFetcherAppState,
) {
    composable(MainScreen.Home.createRoute(Screen.Home)) {
        Home()
    }
}

private fun NavGraphBuilder.addProfile(
    appState: MovieFetcherAppState,
) {
    composable(MainScreen.Profile.createRoute(Screen.Profile)) {
        Profile()
    }
}