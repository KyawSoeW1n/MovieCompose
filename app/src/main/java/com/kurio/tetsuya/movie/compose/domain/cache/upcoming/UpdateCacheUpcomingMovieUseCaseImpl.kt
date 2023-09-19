package com.kurio.tetsuya.movie.compose.domain.cache.upcoming

import com.kurio.tetsuya.movie.compose.data.cache.dao.UpcomingDao
import javax.inject.Inject

class UpdateCacheUpcomingMovieUseCaseImpl @Inject constructor(private val upcomingDao: UpcomingDao) :
    UpdateCacheUpcomingMovieUseCase {
    override fun updateCacheUpcomingMovie(id: Int, flag: Boolean) {
        upcomingDao.updateUpcoming(id = id, flag = flag)
    }

}