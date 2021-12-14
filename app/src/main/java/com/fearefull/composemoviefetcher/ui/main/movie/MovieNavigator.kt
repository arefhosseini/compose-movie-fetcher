package com.fearefull.composemoviefetcher.ui.main.movie

import com.fearefull.composemoviefetcher.base.ViewEvent
import com.fearefull.composemoviefetcher.base.ViewSideEffect
import com.fearefull.composemoviefetcher.base.ViewState
import com.fearefull.composemoviefetcher.data.model.other.Trend

class MovieNavigator {
    sealed class Event : ViewEvent {

    }

    data class State(
        val trends: List<Trend> = listOf(),
        val loading: Boolean = false,
        val error: Throwable? = null
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {
        }
    }
}