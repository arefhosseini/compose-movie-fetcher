package com.fearefull.composemoviefetcher.data.model.other

import com.fearefull.composemoviefetcher.data.model.remote.GenderType

class Celebrity (
    val id: Long,
    val name: String,
    val gender: GenderType,
    val profilePath: String?,
)