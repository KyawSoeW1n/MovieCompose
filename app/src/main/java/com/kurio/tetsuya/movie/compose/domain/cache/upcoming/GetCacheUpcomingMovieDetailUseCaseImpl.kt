package com.kurio.tetsuya.movie.compose.domain.cache.upcoming

import com.kurio.tetsuya.movie.compose.data.cache.dao.PopularDao
import com.kurio.tetsuya.movie.compose.data.cache.dao.UpcomingDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCacheUpcomingMovieDetailUseCaseImpl @Inject constructor(private val upcomingDao: UpcomingDao) :
    GetCacheUpcomingMovieDetailUseCase {
    override fun getMovieFavouriteStatus(id: Int): Flow<Boolean> = upcomingDao.getFavouriteStatus(id)
}