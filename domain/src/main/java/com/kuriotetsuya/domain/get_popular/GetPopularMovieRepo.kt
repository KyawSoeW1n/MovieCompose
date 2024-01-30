package com.kuriotetsuya.domain.get_popular

import com.kuriotetsuya.domain.model.MovieItemVO
import kotlinx.coroutines.flow.Flow


interface GetPopularMovieRepo {
    fun getPopularMovie(): Flow<List<MovieItemVO>>
}