package com.kurio.tetsuya.movie.compose.di

import android.content.Context
import androidx.room.Room
import com.kurio.tetsuya.movie.compose.data.cache.DatabaseConstants
import com.kurio.tetsuya.movie.compose.data.cache.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [CacheModule::class])
class TestDatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        MovieDatabase::class.java,
        DatabaseConstants.DB_NAME
    ).build()

    @Singleton
    @Provides
    fun provideUpcomingDao(db: MovieDatabase) = db.upcomingDao()

    @Singleton
    @Provides
    fun providePopularDao(db: MovieDatabase) = db.popularDao()
}