package com.kurio.tetsuya.movie.compose.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface  AppModule {
//    @Binds
//    @Singleton
//    fun bindAppPreferences(impl: AppPreferencesDataStoreDataStoreImpl): AppPreferencesDataStore
}