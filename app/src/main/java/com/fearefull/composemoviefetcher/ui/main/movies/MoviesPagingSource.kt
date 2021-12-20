package com.fearefull.composemoviefetcher.ui.main.movies

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fearefull.composemoviefetcher.data.model.other.Movie
import com.fearefull.composemoviefetcher.data.model.remote.toMovies
import com.fearefull.composemoviefetcher.data.remote.RepositoryMovie

class MoviesPagingSource (
    private val repositoryMovie: RepositoryMovie,
    private val onDataWasLoaded: () -> Unit
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val responseMovie = repositoryMovie.fetchMovies(page)
            val loadResult = LoadResult.Page(
                data = responseMovie.toMovies(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = responseMovie.page.plus(1)
            )
            onDataWasLoaded()
            loadResult
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}