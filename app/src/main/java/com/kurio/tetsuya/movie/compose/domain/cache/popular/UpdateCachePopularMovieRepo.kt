package com.kurio.tetsuya.movie.compose.domain.cache.popular

interface UpdateCachePopularMovieRepo {
    fun updateCachePopularMovie(id: Int, flag: Boolean)
}