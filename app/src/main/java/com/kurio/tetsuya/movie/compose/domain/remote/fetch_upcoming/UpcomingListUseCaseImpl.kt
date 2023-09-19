package com.kurio.tetsuya.movie.compose.domain.remote.fetch_upcoming

import com.kurio.tetsuya.movie.compose.data.remote.impl.upcoming.UpcomingListRepoImpl
import javax.inject.Inject


class UpcomingListUseCaseImpl @Inject constructor(private val upcomingListRepoImpl: UpcomingListRepoImpl) :
    UpcomingListUseCase {
    override fun getUpcomingList() = upcomingListRepoImpl.getUpcomingList()

}