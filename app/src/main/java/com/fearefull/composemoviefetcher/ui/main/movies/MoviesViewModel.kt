package com.fearefull.composemoviefetcher.ui.main.movies

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fearefull.composemoviefetcher.base.BaseViewModel
import com.fearefull.composemoviefetcher.data.model.other.Movie
import com.fearefull.composemoviefetcher.data.remote.RepositoryMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repositoryMovie: RepositoryMovie,
) : BaseViewModel<MoviesNavigator.Event, MoviesNavigator.State, MoviesNavigator.Effect>() {
//    val movies: Flow<PagingData<Movie>> = Pager(
//        config = PagingConfig(pageSize = 20),
//        pagingSourceFactory = ::initPagingSource)
//        .flow.cachedIn(viewModelScope)

//    lateinit var pagingSource: MoviesPagingSource
//        private set

    init {
        val movies: Flow<PagingData<Movie>> = Pager(
            config = PagingConfig(pageSize = 20),
            ) {
            MoviesPagingSource(
                repositoryMovie,
                onDataWasLoaded = {setEffect { MoviesNavigator.Effect.DataWasLoaded }}
            )
        }
            .flow.cachedIn(viewModelScope)
        setState {
            copy(movies = movies)
        }
    }

    override fun setInitialState() =
        MoviesNavigator.State(loading = true)

    override fun handleEvents(event: MoviesNavigator.Event) {
        when(event) {
            is MoviesNavigator.Event.MovieSelection -> {
                setEffect { MoviesNavigator.Effect.Navigation.ToMovieDetails(event.movie) }
            }
        }
    }

//    private fun initPagingSource() = MoviesPagingSource(
//        repositoryMovie,
//        onDataWasLoaded = {setEffect { MoviesNavigator.Effect.DataWasLoaded }}
//    ).also(::pagingSource::set)
}