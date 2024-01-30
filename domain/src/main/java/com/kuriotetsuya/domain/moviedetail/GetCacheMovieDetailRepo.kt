package com.kuriotetsuya.domain.moviedetail

import kotlinx.coroutines.flow.Flow

interface GetCacheMovieDetailRepo {
    fun getMovieDetail(movieId: Int): Flow<Boolean>
}