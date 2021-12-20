package com.fearefull.composemoviefetcher.ui.main.movie_details

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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun MovieDetailsScreen(
    state: MovieDetailsNavigator.State,
    effectFlow: Flow<MovieDetailsNavigator.Effect>?,
    onEventSent: (event: MovieDetailsNavigator.Event) -> Unit,
    onNavigationSent: (navigationEffect: MovieDetailsNavigator.Effect.Navigation) -> Unit,
) {

    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when(effect) {
                is MovieDetailsNavigator.Effect.DataWasLoaded -> {

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
                Text("MOVIE DETAILS")
            }
        }
        state.movie != null -> {
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(state.movie.title)
            }
        }
    }
}