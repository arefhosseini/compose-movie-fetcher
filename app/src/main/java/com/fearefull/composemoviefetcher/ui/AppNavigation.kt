package com.fearefull.composemoviefetcher.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fearefull.composemoviefetcher.ui.auth.sign_in.SignInNavigator
import com.fearefull.composemoviefetcher.ui.auth.sign_in.SignInScreen
import com.fearefull.composemoviefetcher.ui.auth.sign_in.SignInViewModel
import com.fearefull.composemoviefetcher.ui.auth.sign_up.SignUpNavigator
import com.fearefull.composemoviefetcher.ui.auth.sign_up.SignUpScreen
import com.fearefull.composemoviefetcher.ui.auth.sign_up.SignUpViewModel
import com.fearefull.composemoviefetcher.ui.main.MainScreen
import com.fearefull.composemoviefetcher.ui.splash.SplashNavigator
import com.fearefull.composemoviefetcher.ui.splash.SplashScreen
import com.fearefull.composemoviefetcher.ui.splash.SplashViewModel

/**
 * Created by Aref Hosseini on ۱۶/۱۱/۲۰۲۱.
 */

sealed class RouteScreen(val route: String) {
    object Splash : RouteScreen("splash")
    object Auth : RouteScreen("auth")
    object Main : RouteScreen("main")
}

sealed class RouteScreenAuth(val route: String) {
    object SignUp : RouteScreenAuth("signUp")
    object SignIn : RouteScreenAuth("signIn")
}

@Composable
internal fun AppNavigation(
    appState: MovieFetcherAppState,
) {
    NavHost(
        navController = appState.navController,
        startDestination = RouteScreen.Splash.route,
    ) {
        addSplash(appState)
        addAuthTopLevel(appState)
        addMain(appState)
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
                        appState.navigateToAuth(it)
                    }
                }
            }
        )

    }
}

private fun NavGraphBuilder.addAuthTopLevel(
    appState: MovieFetcherAppState
) {
    navigation(
        startDestination = RouteScreenAuth.SignIn.route,
        route = RouteScreen.Auth.route
    ) {
        addSignUp(appState)
        addSignIn(appState)
    }
}

private fun NavGraphBuilder.addMain(
    appState: MovieFetcherAppState
) {
    composable(route = RouteScreen.Main.route) {
        MainScreen(appState)
    }
}

private fun NavGraphBuilder.addSignUp(
    appState: MovieFetcherAppState,
) {
    composable(route = RouteScreenAuth.SignUp.route) {
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
    composable(route = RouteScreenAuth.SignIn.route) {
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
