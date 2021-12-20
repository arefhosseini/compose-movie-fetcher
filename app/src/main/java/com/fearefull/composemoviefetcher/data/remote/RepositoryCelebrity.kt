package com.fearefull.composemoviefetcher.data.remote

import android.content.res.Resources
import androidx.annotation.WorkerThread
import com.fearefull.composemoviefetcher.data.model.other.Celebrity
import com.fearefull.composemoviefetcher.data.model.remote.ResponseCelebrity
import com.fearefull.composemoviefetcher.data.model.remote.ResponseList
import com.fearefull.composemoviefetcher.data.model.remote.toCelebrity
import javax.inject.Inject

class RepositoryCelebrity @Inject constructor(
    private val service: ServiceCelebrity
) {
    @WorkerThread
    suspend fun fetchCelebrities(
        page: Int
    ): ResponseList<ResponseCelebrity> {
        return service.fetchCelebrities(
            page
        ).body() ?: throw Resources.NotFoundException()
    }

    @WorkerThread
    suspend fun fetchCelebrityDetails(
        celebrityId: Long
    ): Celebrity {
        return service.fetchCelebrityDetails(celebrityId)
            .body()?.toCelebrity() ?: throw Resources.NotFoundException()
    }
}