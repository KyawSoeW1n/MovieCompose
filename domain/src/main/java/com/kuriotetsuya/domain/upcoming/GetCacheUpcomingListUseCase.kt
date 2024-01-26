package com.kuriotetsuya.domain.upcoming

import com.kuriotetsuya.domain.model.MovieItemVO
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

interface GetCacheUpcomingListUseCase {
    fun getUpcomingList(keyword: String): Flow<ImmutableList<MovieItemVO>>
}