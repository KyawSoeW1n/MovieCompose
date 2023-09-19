package com.kurio.tetsuya.movie.compose.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kurio.tetsuya.movie.compose.data.cache.dao.PopularDao
import com.kurio.tetsuya.movie.compose.data.cache.dao.UpcomingDao
import com.kurio.tetsuya.movie.compose.data.cache.entity.PopularCacheMovie
import com.kurio.tetsuya.movie.compose.data.cache.entity.UpcomingCacheMovie

@Database(
    entities = [PopularCacheMovie::class, UpcomingCacheMovie::class],
    version = 1,
    exportSchema = true
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun upcomingDao(): UpcomingDao
    abstract fun popularDao(): PopularDao
}
