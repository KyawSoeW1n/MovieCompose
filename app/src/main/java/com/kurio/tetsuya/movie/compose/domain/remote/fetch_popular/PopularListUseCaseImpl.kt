package com.kurio.tetsuya.movie.compose.domain.remote.fetch_popular

import com.kurio.tetsuya.movie.compose.data.remote.impl.popular.PopularListRepoImpl
import javax.inject.Inject


class PopularListUseCaseImpl @Inject constructor(private val popularListRepoImpl: PopularListRepoImpl) :
    PopularListUseCase {
    override suspend fun getPopularList() = popularListRepoImpl.getPopularList()

}