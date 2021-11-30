package com.fearefull.composemoviefetcher.util.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")
    }

    companion object {
        private const val API_KEY = "api_key"
    }
}