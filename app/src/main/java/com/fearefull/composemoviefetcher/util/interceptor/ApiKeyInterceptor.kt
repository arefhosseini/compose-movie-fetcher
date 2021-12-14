package com.fearefull.composemoviefetcher.util.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader(AUTHORIZATION, "$AUTHORIZATION_TYPE $apiKey").build()

        return chain.proceed(request)
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val AUTHORIZATION_TYPE = "Bearer"
    }
}