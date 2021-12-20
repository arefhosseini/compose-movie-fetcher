package com.fearefull.composemoviefetcher.ui.main.movies

import androidx.paging.PagingData
import com.fearefull.composemoviefetcher.base.ViewEvent
import com.fearefull.composemoviefetcher.base.ViewSideEffect
import com.fearefull.composemoviefetcher.base.ViewState
import com.fearefull.composemoviefetcher.data.model.other.Movie
import kotlinx.coroutines.flow.Flow

class MoviesNavigator {
    sealed class Event : ViewEvent {
        data class MovieSelection(val movie: Movie) : Event()
    }

    data class State(
        val movies: Flow<PagingData<Movie>>? = null,
        val loading: Boolean = false,
        val error: Throwable? = null,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {
            data class ToMovieDetails(val movie: Movie) : Navigation()
        }
    }
}