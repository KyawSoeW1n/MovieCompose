package com.kuriotetsuya.domain.fetch_upcoming

import javax.inject.Inject


class FetchUpcomingMovieUseCase @Inject constructor(private val fetchUpcomingMovieRepo: FetchUpcomingMovieRepo) {
    fun getUpcomingList() = fetchUpcomingMovieRepo.fetchUpcomingList()
}