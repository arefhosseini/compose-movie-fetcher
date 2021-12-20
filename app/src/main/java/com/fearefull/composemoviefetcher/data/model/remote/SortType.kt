package com.fearefull.composemoviefetcher.data.model.remote

import com.google.gson.annotations.SerializedName

enum class SortType {
    @SerializedName("popularity.asc")
    POPULARITY_ASCENDING,

    @SerializedName("popularity.desc")
    POPULARITY_DESCENDING,

    @SerializedName("release_date.asc")
    RELEASE_DATE_ASCENDING,

    @SerializedName("release_date.desc")
    RELEASE_DATE_DESCENDING,

    @SerializedName("original_title.asc")
    ORIGINAL_TITLE_ASCENDING,

    @SerializedName("original_title.desc")
    ORIGINAL_TITLE_DESCENDING,

    @SerializedName("vote_average.asc")
    VOTE_AVERAGE_ASCENDING,

    @SerializedName("vote_average.desc")
    VOTE_AVERAGE_DESCENDING,

    @SerializedName("vote_count.asc")
    VOTE_COUNT_ASCENDING,

    @SerializedName("vote_count.desc")
    VOTE_COUNT_DESCENDING,
}