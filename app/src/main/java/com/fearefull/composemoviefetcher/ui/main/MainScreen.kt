package com.fearefull.composemoviefetcher.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import com.fearefull.composemoviefetcher.ui.MovieFetcherAppState
import com.fearefull.composemoviefetcher.ui.components.MainBottomNavigation
import com.google.accompanist.insets.ProvideWindowInsets

/**
 * Created by Aref Hosseini on ۱۷/۱۱/۲۰۲۱.
 */

@Composable
fun MainScreen(
    appState: MovieFetcherAppState
) {
    ProvideWindowInsets() {
        val mainState = rememberMainState()
        Scaffold(
            bottomBar = {
                val currentSelectedItem by mainState.navController.currentScreenAsState()
                MainBottomNavigation(
                    selectedNavigation = currentSelectedItem,
                    onNavigationSelected = mainState::onMainBottomNavigationSelected,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ) {
            MainNavigation(
                mainState = mainState,
                appState = appState,
                modifier = Modifier.padding(it)
            )
        }
    }
}

/**
 * Adds an [NavController.OnDestinationChangedListener] to this [NavController] and updates the
 * returned [State] which is updated as the destination changes.
 */
@Stable
@Composable
private fun NavController.currentScreenAsState(): State<RouteScreenMain> {
    val selectedItem = remember { mutableStateOf<RouteScreenMain>(RouteScreenMain.DEFAULT_TAB) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == RouteScreenMain.Movie.route } -> {
                    selectedItem.value = RouteScreenMain.Movie
                }
                destination.hierarchy.any { it.route == RouteScreenMain.Celebrity.route } -> {
                    selectedItem.value = RouteScreenMain.Celebrity
                }
                destination.hierarchy.any { it.route == RouteScreenMain.Profile.route } -> {
                    selectedItem.value = RouteScreenMain.Profile
                }
            }
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedItem
}