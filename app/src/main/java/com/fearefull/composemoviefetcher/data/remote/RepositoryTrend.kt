package com.fearefull.composemoviefetcher.data.remote

import android.content.res.Resources
import androidx.annotation.WorkerThread
import com.fearefull.composemoviefetcher.data.model.other.Trend
import com.fearefull.composemoviefetcher.data.model.remote.MediaType
import com.fearefull.composemoviefetcher.data.model.remote.toTrends
import javax.inject.Inject

class RepositoryTrend @Inject constructor(
    private val service: ServiceTrend
) {
    @WorkerThread
    suspend fun fetchTrends(mediaType: MediaType): List<Trend> {
        return service.trendsList(mediaType).body()?.toTrends() ?: throw Resources.NotFoundException()
    }
}