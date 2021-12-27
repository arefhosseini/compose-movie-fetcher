package com.fearefull.composemoviefetcher.data.local

import android.content.SharedPreferences
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AppPreferences (
    private val sharedPreferences: SharedPreferences
) {
    companion object {
        private const val USER_KEY = "user"
        const val PREFERENCES_NAME = "shared_pref"
    }

//    var user: User?
//        get() = sharedPrefer.getString(USER_KEY, null)?.let { Json.decodeFromString<User>(it) }
//        set(value) = value
//            ?.let { sharedPrefer.edit().putString(USER_KEY, Json.encodeToString(value)).apply() }
//            ?: sharedPrefer.edit().remove(USER_KEY).apply()
}