package com.kurio.tetsuya.movie.compose.data.cache.impl.popular

import com.kurio.tetsuya.movie.compose.data.cache.dao.PopularDao
import javax.inject.Inject

class UpdateCachePopularMovieRepoImpl @Inject constructor(private val popularDao: PopularDao) :
    UpdateCachePopularMovieRepo {
    override fun updateCachePopularMovie(id: Int, flag: Boolean) {
        popularDao.updatePopular(id = id, flag = flag)
    }
}