package com.kurio.tetsuya.movie.compose.domain.cache.popular

import kotlinx.coroutines.flow.Flow

interface GetCachePopularMovieDetailUseCase {
    fun getMovieFavouriteStatus(id : Int) : Flow<Boolean>
}