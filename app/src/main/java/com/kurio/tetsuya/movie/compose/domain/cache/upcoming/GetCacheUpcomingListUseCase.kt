package com.kurio.tetsuya.movie.compose.domain.cache.upcoming

import com.kurio.tetsuya.movie.compose.data.remote.model.movie.MovieItemVO
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

interface GetCacheUpcomingListUseCase {
    fun getUpcomingList(keyword: String): Flow<ImmutableList<MovieItemVO>>
}