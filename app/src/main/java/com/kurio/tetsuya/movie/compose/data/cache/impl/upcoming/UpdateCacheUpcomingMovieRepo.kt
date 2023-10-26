package com.kurio.tetsuya.movie.compose.data.cache.impl.upcoming

interface UpdateCacheUpcomingMovieRepo {
    fun updateCacheUpcomingMovie(id: Int, flag: Boolean)
}