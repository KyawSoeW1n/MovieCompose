package com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.impl.movie

import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.datasource.movie.MovieCacheDataSourceImpl
import com.kuriotetsuya.domain.update_favourite_status.UpdateFavouriteStatusRepo
import javax.inject.Inject

class UpdateFavouriteStatusImpl @Inject constructor(
    private val movieCacheDataSourceImpl: MovieCacheDataSourceImpl
) : UpdateFavouriteStatusRepo {
    override fun updateFavouriteStatus(movieId: Int, flag: Boolean) =
        movieCacheDataSourceImpl.updateMovie(movieId, flag)
}