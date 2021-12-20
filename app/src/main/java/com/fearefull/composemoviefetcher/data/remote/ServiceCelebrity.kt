package com.fearefull.composemoviefetcher.data.remote

import com.fearefull.composemoviefetcher.data.model.remote.ResponseCelebrity
import com.fearefull.composemoviefetcher.data.model.remote.ResponseList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceCelebrity {
    @GET("person/popular")
    suspend fun fetchCelebrities(
        @Query("page") page: Int,
    ): Response<ResponseList<ResponseCelebrity>>

    @GET("person/{person_id}")
    suspend fun fetchCelebrityDetails(
        @Path("person_id") celebrityId: Long
    ) : Response<ResponseCelebrity>
}