package com.kuriotetsuya.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.entity.MovieTableUpdate
import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.entity.PopularMovie
import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.entity.UpcomingMovie
import com.kuriotetsuya.data.cache.DatabaseConstants
import com.kuriotetsuya.data.cache.PopularTableConstants
import com.kuriotetsuya.data.cache.UpcomingTableConstants
import com.kuriotetsuya.data.cache.entity.MovieTable
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Upsert(entity = MovieTable::class)
    fun insertMovieList(cacheTicketList: List<MovieTableUpdate>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovie(cacheTicketList: List<PopularMovie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingMovie(cacheTicketList: List<UpcomingMovie>)

    @Query("UPDATE ${DatabaseConstants.MOVIE_TABLE} SET isFavourite = :flag WHERE id = :id")
    fun updateMovie(id: Int, flag: Boolean)

    @Query("SELECT isFavourite FROM ${DatabaseConstants.MOVIE_TABLE} WHERE id=:movieId")
    fun getMovieDetail(movieId: Int): Flow<Boolean>

    @Query("SELECT * FROM ${DatabaseConstants.MOVIE_TABLE} ,${DatabaseConstants.UPCOMING_TABLE}  WHERE id=${UpcomingTableConstants.id}")
    fun getUpcomingMovieList(): Flow<List<MovieTable>>

    @Query(
        "SELECT * FROM ${DatabaseConstants.MOVIE_TABLE} ,${DatabaseConstants.POPULAR_TABLE}  WHERE id=${
            PopularTableConstants.id
        }"
    )
    fun getPopularMovieList(): Flow<List<MovieTable>>

    @Query("SELECT * FROM ${DatabaseConstants.MOVIE_TABLE} WHERE title LIKE '%' || :keyword || '%'")
    fun filterByKeyword(keyword: String): Flow<List<MovieTable>>

    @Query("SELECT isFavourite FROM ${DatabaseConstants.MOVIE_TABLE} WHERE id = :id")
    fun getFavouriteStatus(id: Int): Flow<Boolean>
}

