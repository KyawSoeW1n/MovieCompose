package com.kuriotetsuya.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.entity.PopularMovie
import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.entity.UpcomingMovie
import com.kuriotetsuya.data.cache.dao.MovieDao
import com.kuriotetsuya.data.cache.entity.MovieTable

@Database(
    entities = [MovieTable::class, UpcomingMovie::class, PopularMovie::class],
    version = 1,
    exportSchema = true
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun provideMovieDao(): MovieDao
}
