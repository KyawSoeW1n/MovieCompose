package com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.impl.movie

import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.datasource.movie.MovieCacheDataSourceImpl
import com.kuriotetsuya.domain.moviedetail.GetCacheMovieDetailRepo
import javax.inject.Inject

class GetMovieDetailImpl @Inject constructor(
    private val movieCacheDataSourceImpl: MovieCacheDataSourceImpl
) : GetCacheMovieDetailRepo {
    override fun getMovieDetail(movieId: Int) = movieCacheDataSourceImpl.getMovieDetail(movieId)

}