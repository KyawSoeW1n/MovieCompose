package com.kuriotetsuya.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kuriotetsuya.data.cache.dao.MovieDao
import com.kuriotetsuya.data.cache.entity.CacheMovie

@Database(
    entities = [CacheMovie::class],
    version = 1,
    exportSchema = true
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun provideMovieDao(): MovieDao
}
