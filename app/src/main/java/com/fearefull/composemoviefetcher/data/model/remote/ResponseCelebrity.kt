package com.fearefull.composemoviefetcher.data.model.remote

import androidx.compose.runtime.Immutable
import com.fearefull.composemoviefetcher.data.model.other.Celebrity
import com.fearefull.composemoviefetcher.util.constants.AppConstants

@Immutable
class ResponseCelebrity (
    val birthday: String?,
    val known_for_department: String,
    val deathday: String?,
    val id: Long,
    val name: String,
    val also_known_as: List<String>,
    val gender: GenderType,
    val biography: String,
    val popularity: Double,
    val place_of_birth: String?,
    val profile_path: String?,
    val adult: Boolean,
    val imdb_id: String,
    val homepage: String?
)

fun ResponseCelebrity.toCelebrity(): Celebrity {
    return Celebrity(
        id = id,
        name = name,
        gender = gender,
        profilePath = AppConstants.BASE_IMAGE_URL + profile_path
    )
}