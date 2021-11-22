package com.fearefull.composemoviefetcher.ui

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.fearefull.composemoviefetcher.ui.main.MainBottomNavigation
import com.fearefull.composemoviefetcher.ui.theme.ComposeMovieFetcherTheme
import com.google.accompanist.insets.ProvideWindowInsets
import java.util.logging.Level.INFO

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
                    print(appState.navController.currentDestination?.route)
                    Log.i("ss", appState.navController.currentDestination?.route ?: "NULL")
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
private fun NavController.currentScreenAsState(): State<Screen> {
    val selectedItem = remember { mutableStateOf<Screen>(Screen.Home) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == Screen.Home.route } -> {
                    selectedItem.value = Screen.Home
                }
                destination.hierarchy.any { it.route == Screen.Profile.route } -> {
                    selectedItem.value = Screen.Profile
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