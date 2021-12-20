package com.fearefull.composemoviefetcher.ui.main.movie_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fearefull.composemoviefetcher.base.BaseViewModel
import com.fearefull.composemoviefetcher.data.model.remote.toMovie
import com.fearefull.composemoviefetcher.data.remote.RepositoryMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repositoryMovie: RepositoryMovie
) : BaseViewModel<MovieDetailsNavigator.Event, MovieDetailsNavigator.State, MovieDetailsNavigator.Effect>() {
    private val movieId: Long = savedStateHandle.get("movieId")!!


    init {
        viewModelScope.launch {
            fetchMovieDetails()
        }
    }

    override fun setInitialState() = MovieDetailsNavigator.State(loading = true)

    override fun handleEvents(event: MovieDetailsNavigator.Event) {
        when(event) {

        }
    }

    private suspend fun fetchMovieDetails() {
        val movie = repositoryMovie.fetchMovieDetails(movieId)
        setState {
            copy(movie = movie, loading = false)
        }
        setEffect { MovieDetailsNavigator.Effect.DataWasLoaded }
    }
}