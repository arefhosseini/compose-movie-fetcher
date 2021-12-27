package com.fearefull.composemoviefetcher.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fearefull.composemoviefetcher.ui.lifecycleIsResumed

@Composable
fun rememberMainState(
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    MainState(navController)
}

class MainState(
    val navController: NavHostController
) {
    private val currentRoute: String?
        get() = navController.currentDestination?.route

    fun navigateBack() = navController.popBackStack()

    fun navigateToMovieDetails(from: NavBackStackEntry, root: RouteScreenMain, movieId: Long) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(RouteScreenMainLeaf.MovieDetails.createRoute(root, movieId))
        }
    }

    fun navigateToCelebrityDetails(from: NavBackStackEntry, root: RouteScreenMain, celebrityId: Long) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(RouteScreenMainLeaf.CelebrityDetails.createRoute(root, celebrityId))
        }
    }

    fun onMainBottomNavigationSelected(selected: RouteScreenMain) {
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
}