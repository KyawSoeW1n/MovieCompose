package com.kurio.tetsuya.movie.compose.di

import com.kurio.tetsuya.movie.compose.data.cache.app.AppPreferencesDataStore
import com.kurio.tetsuya.movie.compose.data.cache.app.AppPreferencesDataStoreDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface  AppModule {
    @Binds
    @Singleton
    fun bindAppPreferences(impl: AppPreferencesDataStoreDataStoreImpl): AppPreferencesDataStore
}