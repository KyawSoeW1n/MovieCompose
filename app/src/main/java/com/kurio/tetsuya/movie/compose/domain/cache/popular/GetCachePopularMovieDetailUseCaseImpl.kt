package com.kurio.tetsuya.movie.compose.domain.cache.popular

import com.kurio.tetsuya.movie.compose.data.cache.dao.PopularDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCachePopularMovieDetailUseCaseImpl @Inject constructor(private val popularDao: PopularDao) :
    GetCachePopularMovieDetailUseCase {
    override fun getMovieFavouriteStatus(id: Int): Flow<Boolean> = popularDao.getFavouriteStatus(id)
}