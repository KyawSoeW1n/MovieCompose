package com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.datasource.movie

import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.entity.PopularMovie
import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.entity.UpcomingMovie
import com.kuriotetsuya.data.cache.entity.MovieTable
import kotlinx.coroutines.flow.Flow

interface MovieCacheDataSource {
    fun getUpcomingMovieList(): Flow<List<MovieTable>>
    fun getPopularMovieList(): Flow<List<MovieTable>>

    fun insertMovieList(list: List<MovieTable>)
    fun insertPopularMovieList(list: List<PopularMovie>)
    fun insertUpcomingMovieList(list: List<UpcomingMovie>)
    fun updateMovie(movieId: Int, flag: Boolean)
    fun getMovieDetail(movieId: Int): Flow<Boolean>
}