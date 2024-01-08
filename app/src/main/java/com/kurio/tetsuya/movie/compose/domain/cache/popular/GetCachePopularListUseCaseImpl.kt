package com.kurio.tetsuya.movie.compose.domain.cache.popular

import com.kurio.tetsuya.movie.compose.data.cache.dao.PopularDao
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.MovieItemVO
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCachePopularListUseCaseImpl @Inject constructor(private val popularDao: PopularDao) :
    GetCachePopularListUseCase {
    override fun getCachePopularList(): Flow<ImmutableList<MovieItemVO>> {
        return popularDao.getPopularList().map {
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