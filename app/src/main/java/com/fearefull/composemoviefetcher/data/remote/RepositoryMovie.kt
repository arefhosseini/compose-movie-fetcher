package com.fearefull.composemoviefetcher.data.remote

import android.content.res.Resources
import androidx.annotation.WorkerThread
import com.fearefull.composemoviefetcher.data.model.other.Movie
import com.fearefull.composemoviefetcher.data.model.remote.ResponseList
import com.fearefull.composemoviefetcher.data.model.remote.ResponseMovie
import com.fearefull.composemoviefetcher.data.model.remote.SortType
import com.fearefull.composemoviefetcher.data.model.remote.toMovies
import javax.inject.Inject

class RepositoryMovie @Inject constructor(
    private val service: ServiceMovie
) {
    @WorkerThread
    suspend fun fetchMovies(
        page: Int,
        sortType: SortType? = null,
        includeAdult: Boolean? = null
    ): ResponseList<ResponseMovie> {
        return service.fetchMovies(
            page = page,
            sortType = sortType,
            includeAdult = includeAdult
        ).body() ?: throw Resources.NotFoundException()
    }
}