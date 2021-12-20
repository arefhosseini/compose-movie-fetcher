package com.fearefull.composemoviefetcher.data.model.remote

import com.google.gson.annotations.SerializedName

enum class GenderType {
    @SerializedName("0")
    UNKNOWN,

    @SerializedName("1")
    FEMALE,

    @SerializedName("2")
    MALE
}