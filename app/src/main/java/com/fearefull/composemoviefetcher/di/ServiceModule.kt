package com.fearefull.composemoviefetcher.di

import com.fearefull.composemoviefetcher.data.remote.ServiceTrend
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideServiceTrend(retrofit: Retrofit): ServiceTrend {
        return retrofit.create(ServiceTrend::class.java)
    }
}