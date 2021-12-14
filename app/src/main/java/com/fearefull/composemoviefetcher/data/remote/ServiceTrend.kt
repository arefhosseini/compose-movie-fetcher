package com.fearefull.composemoviefetcher.data.remote

import com.fearefull.composemoviefetcher.data.model.remote.MediaType
import com.fearefull.composemoviefetcher.data.model.remote.ResponseTrends
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceTrend {
    @GET("trending/{media_type}/week")
    suspend fun trendsList(
        @Path("media_type") mediaType: MediaType
    ): Response<ResponseTrends>
}