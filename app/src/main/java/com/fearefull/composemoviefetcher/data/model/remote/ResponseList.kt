package com.fearefull.composemoviefetcher.data.model.remote

import androidx.compose.runtime.Immutable
import com.fearefull.composemoviefetcher.data.model.other.Movie

@Immutable
class ResponseList<T> (
    val page: Int,
    val results: List<T>,
    val total_page: Int,
    val total_results: Int
)

fun ResponseList<ResponseMovie>.toMovies(): List<Movie> {
    return results.map { it.toMovie() }
}