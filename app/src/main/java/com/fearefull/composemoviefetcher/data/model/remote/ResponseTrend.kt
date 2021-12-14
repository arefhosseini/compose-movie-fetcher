package com.fearefull.composemoviefetcher.data.model.remote

import androidx.compose.runtime.Immutable
import com.fearefull.composemoviefetcher.data.model.other.Trend
import com.fearefull.composemoviefetcher.util.constants.AppConstants.BASE_IMAGE_URL

@Immutable
class ResponseTrends (
    val page: Int,
    val results: List<ResponseTrend>,
    val total_page: Int,
    val total_results: Int
)

@Immutable
class ResponseTrend (
    val poster_path: String?,
    val adult: Boolean,
    val overview: String,
    val release_date: String?,
    val genre_ids: List<Int>,
    val id: Long,
    val original_title: String,
    val original_language: String,
    val title: String?,
    val backdrop_path: String?,
    val popularity: Double,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Double,
)

fun ResponseTrend.toTrend() : Trend {
    return Trend(
        posterPath = BASE_IMAGE_URL + poster_path,
        releaseDate = release_date?: "",
        id = id,
        title = title?: "",
        popularity = popularity,
        voteAverage = vote_average
    )
}

fun ResponseTrends.toTrends(): List<Trend> {
    return results.map { it.toTrend() }
}