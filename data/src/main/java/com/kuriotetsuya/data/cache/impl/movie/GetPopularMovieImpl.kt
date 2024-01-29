package com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.impl.movie

import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.datasource.movie.MovieCacheDataSourceImpl
import com.kuriotetsuya.domain.get_popular.GetPopularMovieRepo
import com.kuriotetsuya.domain.model.MovieItemVO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetPopularMovieImpl @Inject constructor(
    private val movieCacheDataSourceImpl: MovieCacheDataSourceImpl
) : GetPopularMovieRepo {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getPopularMovie() =
        movieCacheDataSourceImpl.getPopularMovieList().mapLatest { list ->
            list.map {
                MovieItemVO(
                    id = it.id,
                    image = it.image,
                    title = it.title,
                    overview = it.description,
                    isFavourite = it.isFavourite
                )
            }
        }

}