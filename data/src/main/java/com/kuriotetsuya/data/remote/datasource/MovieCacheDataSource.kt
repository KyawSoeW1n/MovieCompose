package com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.remote.datasource

import com.kuriotetsuya.data.cache.entity.CacheMovie
import kotlinx.coroutines.flow.Flow

interface MovieCacheDataSource {
    fun getUpcomingList(): Flow<List<CacheMovie>>

    fun insertUpcomingList(list: List<CacheMovie>)
    fun updateMovie(movieId : Int, flag : Boolean)
}