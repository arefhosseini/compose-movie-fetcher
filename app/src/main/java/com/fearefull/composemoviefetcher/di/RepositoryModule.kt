package com.fearefull.composemoviefetcher.di

import com.fearefull.composemoviefetcher.data.remote.RepositoryTrend
import com.fearefull.composemoviefetcher.data.remote.ServiceTrend
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepositoryTrend(
        service: ServiceTrend
    ): RepositoryTrend {
        return RepositoryTrend(service)
    }
}