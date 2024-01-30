package com.kuriotetsuya.domain.moviedetail

import javax.inject.Inject


class MovieDetailUseCase @Inject constructor(private val movieDetailRepo: MovieDetailRepo) {
    fun getMovieDetail(movieId: Int) = movieDetailRepo.getMovieDetail(movieId = movieId)
}