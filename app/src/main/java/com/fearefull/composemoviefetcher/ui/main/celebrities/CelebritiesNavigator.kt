package com.fearefull.composemoviefetcher.ui.main.celebrities

import androidx.paging.PagingData
import com.fearefull.composemoviefetcher.base.ViewEvent
import com.fearefull.composemoviefetcher.base.ViewSideEffect
import com.fearefull.composemoviefetcher.base.ViewState
import com.fearefull.composemoviefetcher.data.model.other.Celebrity
import kotlinx.coroutines.flow.Flow

class CelebritiesNavigator {
    sealed class Event : ViewEvent {
        data class CelebritySelection(val celebrity: Celebrity) : Event()
    }

    data class State(
        val celebrities: Flow<PagingData<Celebrity>>? = null
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation: Effect() {
            data class ToCelebrityDetails(val celebrity: Celebrity) : Navigation()
        }
    }
}