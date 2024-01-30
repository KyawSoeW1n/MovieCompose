package com.kuriotetsuya.domain.moviedetail

import javax.inject.Inject


class GetCacheMovieDetailUseCase @Inject constructor(private val getCacheMovieDetailRepo: GetCacheMovieDetailRepo) {
    fun getMovieDetail(movieId: Int) = getCacheMovieDetailRepo.getMovieDetail(movieId = movieId)
}