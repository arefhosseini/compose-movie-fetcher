package com.fearefull.composemoviefetcher.di

import android.content.Context
import com.fearefull.composemoviefetcher.R
import com.fearefull.composemoviefetcher.util.interceptor.ApiKeyInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

private const val BASE_URL = ""

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @IntoSet
    fun provideApiKeyInterceptor(@ApplicationContext context: Context): Interceptor {
        return ApiKeyInterceptor(context.getString(R.string.api_key))
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptors: Set<Interceptor>): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                interceptors().addAll(interceptors)
            }.build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}