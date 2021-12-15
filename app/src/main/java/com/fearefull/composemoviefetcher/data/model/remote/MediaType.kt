package com.fearefull.composemoviefetcher.data.model.remote

import com.google.gson.annotations.SerializedName

enum class MediaType {
    @SerializedName("all")
    ALL,
    @SerializedName("movie")
    MOVIE,
    @SerializedName("tv")
    TV,
    @SerializedName("person")
    PERSON
}