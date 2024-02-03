package com.kurio.tetsuya.movie.compose.di

import android.content.Context
import androidx.room.Room
import com.kuriotetsuya.data.cache.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [DatabaseModule::class])
class TestDatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        MovieDatabase::class.java,
        com.kuriotetsuya.data.cache.DatabaseConstants.DB_NAME
    ).build()

    @Singleton
    @Provides
    fun provideMovieDao(db: MovieDatabase) = db.provideMovieDao()
}