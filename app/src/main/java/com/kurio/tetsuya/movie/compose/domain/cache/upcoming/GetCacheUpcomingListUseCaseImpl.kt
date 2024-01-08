package com.kurio.tetsuya.movie.compose.domain.cache.upcoming

import com.kurio.tetsuya.movie.compose.data.cache.dao.UpcomingDao
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.MovieItemVO
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCacheUpcomingListUseCaseImpl @Inject constructor(private val upcomingDao: UpcomingDao) :
    GetCacheUpcomingListUseCase {
    override fun getUpcomingList(keyword: String): Flow<ImmutableList<MovieItemVO>> {
        if (keyword.isEmpty())
            return upcomingDao.getUpcomingList().map {
                it.map { item ->
                    MovieItemVO(
                        id = item.id,
                        image = item.image,
                        title = item.title,
                        overview = item.description,
                        isFavourite = item.isFavourite
                    )
                }.toImmutableList()
            }


        return upcomingDao.getUpcomingListByKeyword(keyword).map {
            it.map { item ->
                MovieItemVO(
                    id = item.id,
                    image = item.image,
                    title = item.title,
                    overview = item.description,
                    isFavourite = item.isFavourite
                )
            }.toImmutableList()
        }
    }
}