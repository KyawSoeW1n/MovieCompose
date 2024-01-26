package com.kuriotetsuya.domain.upcoming

import com.kuriotetsuya.domain.model.MovieItemVO

interface InsertUpcomingListUseCase {
    fun insertUpcomingList(carList: List<MovieItemVO>)
}