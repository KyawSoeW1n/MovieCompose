package com.kuriotetsuya.domain.upcoming

import kotlinx.coroutines.flow.Flow

interface GetCacheUpcomingMovieDetailUseCase {
    fun getMovieFavouriteStatus(id : Int) : Flow<Boolean>
}