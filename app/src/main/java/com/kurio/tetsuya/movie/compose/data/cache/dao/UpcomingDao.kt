package com.kurio.tetsuya.movie.compose.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kurio.tetsuya.movie.compose.data.cache.DatabaseConstants
import com.kurio.tetsuya.movie.compose.data.cache.entity.UpcomingCacheMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface UpcomingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingList(cacheTicketList: List<UpcomingCacheMovie>)

    @Query("UPDATE ${DatabaseConstants.upcomingTableName} SET isFavourite = :flag WHERE id = :id")
    fun updateUpcoming(id: Int, flag: Boolean)

    @Query("SELECT * FROM ${DatabaseConstants.upcomingTableName}")
    fun getUpcomingList(): Flow<List<UpcomingCacheMovie>>
}