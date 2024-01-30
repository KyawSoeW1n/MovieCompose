package com.kuriotetsuya.domain.get_upcoming

import javax.inject.Inject


class GetUpcomingMovieUseCase @Inject constructor(private val getUpcomingMovieRepo: GetUpcomingMovieRepo) {
    fun getUpcomingList(keyword : String) = getUpcomingMovieRepo.getUpcomingList(keyword)
}