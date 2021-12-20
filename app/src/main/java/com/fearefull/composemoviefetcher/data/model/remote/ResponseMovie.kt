package com.fearefull.composemoviefetcher.data.model.remote

import androidx.compose.runtime.Immutable
import com.fearefull.composemoviefetcher.data.model.other.Movie
import com.fearefull.composemoviefetcher.util.constants.AppConstants
import com.google.gson.annotations.SerializedName

@Immutable
class ResponseMovie (
    val adult: Boolean,
    val backdrop_path: String?,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String?,
    val id: Long,
    val imdb_id: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double,
    val poster_path: String?,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int?,
    val spoken_languages: List<SpokenLanguage>,
    val status: MovieStatus,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
)

@Immutable
class Genre (
    val id: Long,
    val name: String
)

@Immutable
class ProductionCompany (
    val name: String,
    val id: Long,
    val logo_path: String?,
    val origin_country: String
)

@Immutable
class ProductionCountry (
    val iso_3166_1: String,
    val name: String
)

@Immutable
class SpokenLanguage (
    val iso_3166_1: String,
    val name: String
)

enum class MovieStatus {
    @SerializedName("Rumored")
    RUMORED,

    @SerializedName("Planned")
    PLANNED,

    @SerializedName("In Production")
    IN_PRODUCTION,

    @SerializedName("Post Production")
    POST_PRODUCTION,

    @SerializedName("Released")
    RELEASED,

    @SerializedName("Canceled")
    CANCELED,
}

fun ResponseMovie.toMovie(): Movie {
    return Movie(
        posterPath = AppConstants.BASE_IMAGE_URL + poster_path,
        releaseDate = release_date ?: "",
        id = id,
        title = title ?: "",
        popularity = popularity,
        voteAverage = vote_average
    )
}