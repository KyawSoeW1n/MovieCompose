package com.kurio.tetsuya.movie.compose.domain.cache.upcoming

import kotlinx.coroutines.flow.Flow

interface GetCacheUpcomingMovieDetailUseCase {
    fun getMovieFavouriteStatus(id : Int) : Flow<Boolean>
}