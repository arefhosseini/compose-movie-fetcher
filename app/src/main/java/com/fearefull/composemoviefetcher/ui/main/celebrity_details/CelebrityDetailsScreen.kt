package com.fearefull.composemoviefetcher.ui.main.celebrity_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.fearefull.composemoviefetcher.base.LAUNCH_LISTEN_FOR_EFFECTS
import com.fearefull.composemoviefetcher.ui.main.movie_details.MovieDetailsNavigator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun CelebrityDetailsScreen(
    state: CelebrityDetailsNavigator.State,
    effectFlow: Flow<CelebrityDetailsNavigator.Effect>?,
    onEventSent: (event: CelebrityDetailsNavigator.Event) -> Unit,
    onNavigationSent: (navigationEffect: CelebrityDetailsNavigator.Effect.Navigation) -> Unit
) {
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when(effect) {
                is CelebrityDetailsNavigator.Effect.DataWasLoaded -> {

                }
            }
        }?.collect()
    }

    when {
        state.loading -> {
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Loading")
            }
        }
        state.celebrity != null -> {
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(state.celebrity.name)
            }
        }
    }
}