package com.fearefull.composemoviefetcher.di

import com.fearefull.composemoviefetcher.data.remote.RepositoryMovie
import com.fearefull.composemoviefetcher.data.remote.ServiceMovie
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
    fun provideRepositoryMovie(
        service: ServiceMovie
    ): RepositoryMovie {
        return RepositoryMovie(service)
    }
}