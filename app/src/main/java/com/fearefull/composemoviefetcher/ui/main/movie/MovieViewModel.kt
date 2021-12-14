package com.fearefull.composemoviefetcher.ui.main.movie

import androidx.lifecycle.viewModelScope
import com.fearefull.composemoviefetcher.base.BaseViewModel
import com.fearefull.composemoviefetcher.data.model.remote.MediaType
import com.fearefull.composemoviefetcher.data.remote.RepositoryTrend
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repositoryTrend: RepositoryTrend,
) : BaseViewModel<MovieNavigator.Event, MovieNavigator.State, MovieNavigator.Effect>() {

    init {
        viewModelScope.launch {
            fetchTrends()
        }
    }

    override fun setInitialState() =
        MovieNavigator.State(trends = listOf(), loading = true)

    override fun handleEvents(event: MovieNavigator.Event) {
        when(event) {
            // TODO: add some events
        }
    }

    private suspend fun fetchTrends() {
        val trends = repositoryTrend.fetchTrends(MediaType.MOVIE)
        setState {
            copy(trends = trends, loading = false)
        }
        setEffect { MovieNavigator.Effect.DataWasLoaded }
    }
}