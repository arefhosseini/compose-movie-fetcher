package com.fearefull.composemoviefetcher.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import timber.log.Timber

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
            navController.navigate(RouteScreenMain.Movie.createRoute(RouteScreen.Movie))
        }
    }

    fun navigateToMovieDetails(from: NavBackStackEntry, root: RouteScreen, movieId: Long) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(RouteScreenMain.MovieDetails.createRoute(root, movieId))
        }
    }

    fun navigateToCelebrityDetails(from: NavBackStackEntry, root: RouteScreen, celebrityId: Long) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(RouteScreenMain.CelebrityDetails.createRoute(root, celebrityId))
        }
    }

    fun onMainBottomNavigationSelected(selected: RouteScreen) {
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
        @Composable get() = RouteScreen.hasRoute(navController
            .currentBackStackEntryAsState().value?.destination?.route)
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED