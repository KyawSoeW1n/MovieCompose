package com.kuriotetsuya.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kuriotetsuya.data.cache.DatabaseConstants
import com.kuriotetsuya.data.cache.entity.CacheMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(cacheTicketList: List<CacheMovie>)

    @Query("UPDATE ${DatabaseConstants.MOVIE_TABLE} SET isFavourite = :flag WHERE id = :id")
    fun updateMovie(id: Int, flag: Boolean)

    @Query("SELECT * FROM ${DatabaseConstants.MOVIE_TABLE}")
    fun getUpcomingList(): Flow<List<CacheMovie>>

    @Query("SELECT * FROM ${DatabaseConstants.MOVIE_TABLE} WHERE title LIKE '%' || :keyword || '%'")
    fun filterByKeyword(keyword: String): Flow<List<CacheMovie>>

    @Query("SELECT isFavourite FROM ${DatabaseConstants.MOVIE_TABLE} WHERE id = :id")
    fun getFavouriteStatus(id: Int): Flow<Boolean>
}

