package com.fearefull.composemoviefetcher.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

/**
 * Created by Aref Hosseini on ۲۰/۱۱/۲۰۲۱.
 */

@Composable
fun rememberMovieFetcherAppState(
    finishActivity: () -> Unit,
    navController: NavHostController = rememberNavController(),
) = remember(navController) {
    MovieFetcherAppState(navController, finishActivity)
}

class MovieFetcherAppState (
    val navController: NavHostController,
    finishActivity: () -> Unit,
    ) {

    // ----------------------------------------------------------
    // Navigation state source of truth
    // ----------------------------------------------------------

    private val currentRoute: String?
        get() = navController.currentDestination?.route

    fun navigateBack() = navController.popBackStack()

    fun navigateToMain(from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(MainScreen.Home.createRoute(Screen.Home))
        }
    }

    fun onMainBottomNavigationSelected(selected: Screen) {
        if (selected.route != currentRoute) {
            navController.navigate(selected.route) {
                launchSingleTop = true
                restoreState = true

                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
            }
        }
    }

    // ----------------------------------------------------------
    // BottomBar state source of truth
    // ----------------------------------------------------------

    // Reading this attribute will cause recompositions when the bottom bar needs shown, or not.
    // Not all routes need to show the bottom bar.
    val shouldShowBottomBar: Boolean
        @Composable get() = Screen.hasRoute(navController
            .currentBackStackEntryAsState().value?.destination?.route)
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED