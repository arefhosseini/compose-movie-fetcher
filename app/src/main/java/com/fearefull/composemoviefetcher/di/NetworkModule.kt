package com.fearefull.composemoviefetcher.di

import android.content.Context
import com.fearefull.composemoviefetcher.R
import com.fearefull.composemoviefetcher.util.EnumConverterFactory
import com.fearefull.composemoviefetcher.util.constants.AppConstants.BASE_URL
import com.fearefull.composemoviefetcher.util.interceptor.ApiKeyInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @IntoSet
    fun provideApiKeyInterceptor(@ApplicationContext context: Context): Interceptor {
        return ApiKeyInterceptor(context.getString(R.string.api_key_v4))
    }

    @Provides
    @Singleton
    fun provideGsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }

    @Provides
    fun provideEnumFactory(): EnumConverterFactory {
        return EnumConverterFactory()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptors: @JvmSuppressWildcards Set<Interceptor>): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                interceptors().addAll(interceptors)
                addInterceptor(HttpLoggingInterceptor { message -> Timber.i(message) }.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonFactory: GsonConverterFactory,
        enumFactory: EnumConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonFactory)
            .addConverterFactory(enumFactory)
            .build()
    }
}