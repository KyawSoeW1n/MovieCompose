package com.kurio.tetsuya.movie.compose.domain.cache.upcoming

import com.kurio.tetsuya.movie.compose.data.remote.model.movie.MovieItemVO

interface InsertUpcomingListUseCase {
    fun insertUpcomingList(carList: List<MovieItemVO>)
}