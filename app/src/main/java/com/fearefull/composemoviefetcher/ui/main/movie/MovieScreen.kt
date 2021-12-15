package com.fearefull.composemoviefetcher.ui.main.movie

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fearefull.composemoviefetcher.base.LAUNCH_LISTEN_FOR_EFFECTS
import com.fearefull.composemoviefetcher.data.model.other.Trend
import com.fearefull.composemoviefetcher.ui.components.TrendItem
import com.fearefull.composemoviefetcher.util.constants.ThemeConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

/**
 * Created by Aref Hosseini on ۱۷/۱۱/۲۰۲۱.
 */

@Composable
fun MovieScreen(
    state: MovieNavigator.State,
    effectFlow: Flow<MovieNavigator.Effect>?,
    onEventSent: (event: MovieNavigator.Event) -> Unit,
    onNavigationSent: (navigationEffect: MovieNavigator.Effect.Navigation) -> Unit
) {

    // Listen for side effects from the VM
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when(effect) {
                is MovieNavigator.Effect.DataWasLoaded -> {

                }
//                is MovieNavigator.Effect.Navigation -> onNavigationSent.
            }
        }?.collect()
    }
    when {
        state.loading -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Loading...")
            }
        }
        state.trends.isNotEmpty() -> {
            TrendsList(trends = state.trends)
        }
    }
}

@Composable
fun TrendsList(
    trends: List<Trend>,
    onItemClicked: (item: Trend) -> Unit = {}
) {
    LazyRow(
        contentPadding = PaddingValues(ThemeConstants.PADDING.dp)
    ) {
        items(trends.size) { index ->
            TrendItem(item = trends[index], onItemClicked = onItemClicked)
        }
    }
}