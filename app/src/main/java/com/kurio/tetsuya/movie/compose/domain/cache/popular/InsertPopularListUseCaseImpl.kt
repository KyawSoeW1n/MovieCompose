package com.kurio.tetsuya.movie.compose.domain.cache.popular

import com.kurio.tetsuya.movie.compose.data.cache.dao.PopularDao
import com.kurio.tetsuya.movie.compose.data.cache.entity.PopularCacheMovie
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.MovieItemVO
import com.kurio.tetsuya.movie.compose.extensions.convertNetworkString
import javax.inject.Inject

class InsertPopularListUseCaseImpl @Inject constructor(
    private val popularDao: PopularDao,
) : InsertPopularListUseCase {
    override fun insertPopularList(carList: List<MovieItemVO>) {
        popularDao.insertPopularList(cacheTicketList = carList.map {
            PopularCacheMovie(
                id = it.id,
                image = it.image.convertNetworkString(),
                title = it.title,
                description = it.overview,
                isFavourite = it.isFavourite,
            )
        }.toList())
    }
}