package com.fearefull.composemoviefetcher.ui.main.movies

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.paging.compose.collectAsLazyPagingItems
import com.fearefull.composemoviefetcher.base.LAUNCH_LISTEN_FOR_EFFECTS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

/**
 * Created by Aref Hosseini on ۱۷/۱۱/۲۰۲۱.
 */

@Composable
fun MoviesScreen(
    state: MoviesNavigator.State,
    effectFlow: Flow<MoviesNavigator.Effect>?,
    onEventSent: (event: MoviesNavigator.Event) -> Unit,
    onNavigationSent: (navigationEffect: MoviesNavigator.Effect.Navigation) -> Unit,
) {
    // Listen for side effects from the VM
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when(effect) {
                is MoviesNavigator.Effect.DataWasLoaded -> {

                }
                is MoviesNavigator.Effect.Navigation.ToMovieDetails ->
                    onNavigationSent(effect)
            }
        }?.collect()
    }
    state.movies ?.let { pagingDataFlow ->
        MoviesContent(pagingDataFlow.collectAsLazyPagingItems()) { movie ->
            onEventSent(MoviesNavigator.Event.MovieSelection(movie))
        }
    } ?: run {
        Surface(color = MaterialTheme.colors.background){}
    }
}

//@Composable
//fun TrendsList(
//    trends: List<Trend>,
//    onItemClicked: (item: Trend) -> Unit = {}
//) {
//    LazyRow(
//        contentPadding = PaddingValues(ThemeConstants.PADDING.dp)
//    ) {
//        items(trends.size) { index ->
//            MoviesGridItem(item = trends[index], onItemClicked = onItemClicked)
//        }
//    }
//}