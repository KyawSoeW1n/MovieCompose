package com.kurio.tetsuya.movie.compose.domain.cache.upcoming

import com.kurio.tetsuya.movie.compose.data.cache.dao.UpcomingDao
import com.kurio.tetsuya.movie.compose.data.cache.entity.UpcomingCacheMovie
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.MovieItemVO
import com.kurio.tetsuya.movie.compose.extensions.convertNetworkString
import kotlinx.collections.immutable.ImmutableList
import javax.inject.Inject

class InsertUpcomingListUseCaseImpl @Inject constructor(
    private val upcomingDao: UpcomingDao
) : InsertUpcomingListUseCase {
    override fun insertUpcomingList(carList: List<MovieItemVO>) {
        upcomingDao.insertUpcomingList(cacheTicketList = carList.map {
            UpcomingCacheMovie(
                id = it.id,
                image = it.image.convertNetworkString(),
                title = it.title,
                description = it.overview,
                isFavourite = it.isFavourite,
            )
        }.toList())
    }

}