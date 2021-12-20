package com.fearefull.composemoviefetcher.data.remote

import com.fearefull.composemoviefetcher.data.model.remote.ResponseList
import com.fearefull.composemoviefetcher.data.model.remote.ResponseMovie
import com.fearefull.composemoviefetcher.data.model.remote.SortType
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceMovie {
    @GET("discover/movie")
    suspend fun fetchMovies(
        @Query("page") page: Int,
        @Query("sort_by") sortType: SortType?,
        @Query("include_adult") includeAdult: Boolean?
    ): Response<ResponseList<ResponseMovie>>

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(
        @Path("movie_id") movieId: Long
    ) : Response<ResponseMovie>
}