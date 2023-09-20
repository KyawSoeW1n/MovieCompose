package com.kurio.tetsuya.movie.compose.di

import android.content.Context
import androidx.room.Room
import com.kurio.tetsuya.movie.compose.data.cache.DatabaseConstants
import com.kurio.tetsuya.movie.compose.data.cache.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {
    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        MovieDatabase::class.java,
        DatabaseConstants.DB_NAME
    ).build()

    @Singleton
    @Provides
    fun providePopularDao(db: MovieDatabase) = db.popularDao()

    @Singleton
    @Provides
    fun provideUpcomingDao(db: MovieDatabase) = db.upcomingDao()
}