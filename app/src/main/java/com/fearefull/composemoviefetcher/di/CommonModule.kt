package com.fearefull.composemoviefetcher.di

import android.content.Context
import com.fearefull.composemoviefetcher.data.local.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CommonModule {

    @Provides
    @ViewModelScoped
    fun provideSharedPreferences(@ApplicationContext context: Context): AppPreferences {
        return AppPreferences(
            context.getSharedPreferences(AppPreferences.PREFERENCES_NAME, Context.MODE_PRIVATE)
        )
    }
}