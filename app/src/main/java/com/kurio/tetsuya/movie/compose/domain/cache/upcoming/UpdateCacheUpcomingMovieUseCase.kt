package com.kurio.tetsuya.movie.compose.domain.cache.upcoming

interface UpdateCacheUpcomingMovieUseCase {
    fun updateCacheUpcomingMovie(id: Int, flag: Boolean)
}