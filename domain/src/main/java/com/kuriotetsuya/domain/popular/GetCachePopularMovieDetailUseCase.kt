package com.kuriotetsuya.domain.popular

import kotlinx.coroutines.flow.Flow

interface GetCachePopularMovieDetailUseCase {
    fun getMovieFavouriteStatus(id : Int) : Flow<Boolean>
}