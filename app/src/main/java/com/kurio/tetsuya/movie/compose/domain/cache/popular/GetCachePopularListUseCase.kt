package com.kurio.tetsuya.movie.compose.domain.cache.popular

import com.kurio.tetsuya.movie.compose.data.remote.model.movie.MovieItemVO
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

interface GetCachePopularListUseCase {
    fun getCachePopularList(): Flow<ImmutableList<MovieItemVO>>
}