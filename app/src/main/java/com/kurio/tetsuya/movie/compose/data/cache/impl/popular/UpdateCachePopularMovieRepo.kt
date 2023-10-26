package com.kurio.tetsuya.movie.compose.data.cache.impl.popular

interface UpdateCachePopularMovieRepo {
    fun updateCachePopularMovie(id: Int, flag: Boolean)
}