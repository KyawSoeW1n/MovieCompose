package com.kurio.tetsuya.movie.compose.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kurio.tetsuya.movie.compose.data.cache.DatabaseConstants
import com.kurio.tetsuya.movie.compose.data.cache.entity.PopularCacheMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularList(cacheTicketList: List<PopularCacheMovie>)

    @Query("UPDATE ${DatabaseConstants.popularTableName} SET isFavourite = :flag WHERE id = :id")
    fun updatePopular(id: Int, flag: Boolean)

    @Query("SELECT * FROM ${DatabaseConstants.popularTableName}")
    fun getPopularList(): Flow<List<PopularCacheMovie>>
}