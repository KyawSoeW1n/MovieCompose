package com.kuriotetsuya.domain.get_popular

import javax.inject.Inject

class GetPopularMovieUseCase @Inject constructor(private val getPopularMovieRepo: GetPopularMovieRepo) {
    fun getPopularList() = getPopularMovieRepo.getPopularMovie()
}