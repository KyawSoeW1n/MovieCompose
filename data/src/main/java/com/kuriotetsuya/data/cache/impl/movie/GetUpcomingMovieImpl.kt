package com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.impl.movie

import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.datasource.movie.MovieCacheDataSourceImpl
import com.kuriotetsuya.domain.get_upcoming.GetUpcomingMovieRepo
import com.kuriotetsuya.domain.model.MovieItemVO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetUpcomingMovieImpl @Inject constructor(
    private val movieCacheDataSourceImpl: MovieCacheDataSourceImpl
) : GetUpcomingMovieRepo {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getUpcomingList(keyword: String) =

        movieCacheDataSourceImpl.getUpcomingMovieList().mapLatest { list ->
            list.filter {
                it.title.lowercase().contains(keyword.lowercase())
            }.map {
                MovieItemVO(
                    id = it.id,
                    image = it.image,
                    title = it.title,
                    overview = it.description,
                    isFavourite = it.isFavourite ?: false
                )
            }
        }

}