package com.kuriotetsuya.domain.fetch_popular

import com.kuriotetsuya.domain.popular.FetchPopularRepo
import javax.inject.Inject


class PopularListUseCaseImpl @Inject constructor(private val fetchPopularRepo: FetchPopularRepo) :
    PopularListUseCase {
    override suspend fun getPopularList() = fetchPopularRepo.getPopularList()

}