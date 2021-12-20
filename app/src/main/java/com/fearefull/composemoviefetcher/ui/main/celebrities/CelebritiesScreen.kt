package com.fearefull.composemoviefetcher.ui.main.celebrities

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.paging.compose.collectAsLazyPagingItems
import com.fearefull.composemoviefetcher.base.LAUNCH_LISTEN_FOR_EFFECTS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun CelebritiesScreen(
    state: CelebritiesNavigator.State,
    effectFlow: Flow<CelebritiesNavigator.Effect>?,
    onEventSent: (event: CelebritiesNavigator.Event) -> Unit,
    onNavigationSent: (navigationEffect: CelebritiesNavigator.Effect.Navigation) -> Unit
) {
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when(effect) {
                is CelebritiesNavigator.Effect.DataWasLoaded -> {

                }
                is CelebritiesNavigator.Effect.Navigation.ToCelebrityDetails -> {
                    onNavigationSent(effect)
                }
            }
        }?.collect()
    }
    state.celebrities?.let { pagingDataFlow ->
        CelebritiesContent(pagingDataFlow.collectAsLazyPagingItems()) { celebrity ->
            onEventSent(CelebritiesNavigator.Event.CelebritySelection(celebrity))
        }
    } ?: run {
        Surface(color = MaterialTheme.colors.background){}
    }
}