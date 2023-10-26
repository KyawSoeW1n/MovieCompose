package com.kurio.tetsuya.movie.compose.data.cache.impl.upcoming

import com.kurio.tetsuya.movie.compose.data.cache.dao.UpcomingDao
import javax.inject.Inject

class UpdateCacheUpcomingMovieRepoImpl @Inject constructor(private val upcomingDao: UpcomingDao) :
    UpdateCacheUpcomingMovieRepo {
    override fun updateCacheUpcomingMovie(id: Int, flag: Boolean) {
        upcomingDao.updateUpcoming(id = id, flag = flag)
    }

}