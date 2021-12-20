package com.fearefull.composemoviefetcher.di

import com.fearefull.composemoviefetcher.data.remote.ServiceMovie
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
    fun provideServiceMovie(retrofit: Retrofit): ServiceMovie {
        return retrofit.create(ServiceMovie::class.java)
    }
}