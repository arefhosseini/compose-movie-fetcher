package com.fearefull.composemoviefetcher.data.model.remote

import androidx.compose.runtime.Immutable

@Immutable
class ResponseCelebrity (
    val adult: Boolean,
    val gender: Int,
    val id: Long,
    val name: String,
    val popularity: Double,
    val profile_path: String
)