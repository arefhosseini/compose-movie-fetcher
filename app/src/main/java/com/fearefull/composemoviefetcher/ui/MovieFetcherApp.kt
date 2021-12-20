package com.fearefull.composemoviefetcher.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import com.fearefull.composemoviefetcher.ui.main.MainBottomNavigation
import com.fearefull.composemoviefetcher.ui.theme.ComposeMovieFetcherTheme
import com.google.accompanist.insets.ProvideWindowInsets
import timber.log.Timber

/**
 * Created by Aref Hosseini on ۱۶/۱۱/۲۰۲۱.
 */

@Composable
fun MovieFetcherComposeApp(finishActivity: () -> Unit) {
    val appState = rememberMovieFetcherAppState(finishActivity)
    ProvideWindowInsets {
        ComposeMovieFetcherTheme {
            Scaffold(
                bottomBar = {
                    if (appState.shouldShowBottomBar) {
                        val currentSelectedItem by appState.navController.currentScreenAsState()
                        MainBottomNavigation(
                            selectedNavigation = currentSelectedItem,
                            onNavigationSelected = appState::onMainBottomNavigationSelected,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            ) {
                AppNavigation(modifier = Modifier.padding(it), appState)
            }
        }
    }
}

/**
 * Adds an [NavController.OnDestinationChangedListener] to this [NavController] and updates the
 * returned [State] which is updated as the destination changes.
 */
@Stable
@Composable
private fun NavController.currentScreenAsState(): State<RouteScreen> {
    val selectedItem = remember { mutableStateOf<RouteScreen>(RouteScreen.Movie) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == RouteScreen.Movie.route } -> {
                    selectedItem.value = RouteScreen.Movie
                }
                destination.hierarchy.any { it.route == RouteScreen.Celebrity.route } -> {
                    selectedItem.value = RouteScreen.Celebrity
                }
                destination.hierarchy.any { it.route == RouteScreen.Profile.route } -> {
                    selectedItem.value = RouteScreen.Profile
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