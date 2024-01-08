package com.kurio.tetsuya.movie.compose.domain.remote.fetch_upcoming

import com.kurio.tetsuya.movie.compose.data.remote.impl.upcoming.UpcomingListRepo
import javax.inject.Inject


class UpcomingListUseCaseImpl @Inject constructor(private val upcomingListRepo: UpcomingListRepo) :
    UpcomingListUseCase {
    override fun getUpcomingList() = upcomingListRepo.getUpcomingList()

}