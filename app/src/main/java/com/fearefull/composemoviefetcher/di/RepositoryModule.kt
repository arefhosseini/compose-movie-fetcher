package com.fearefull.composemoviefetcher.di

import com.fearefull.composemoviefetcher.data.remote.*
import com.google.firebase.auth.FirebaseAuth
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

    @Provides
    @Singleton
    fun provideRepositoryCelebrity(
        service: ServiceCelebrity
    ): RepositoryCelebrity {
        return RepositoryCelebrity(service)
    }

    @Provides
    @Singleton
    fun provideRepositoryAuthenticator(
        firebaseAuth: FirebaseAuth
    ): RepositoryAuthenticator {
        return RepositoryAuthenticator(firebaseAuth)
    }
}