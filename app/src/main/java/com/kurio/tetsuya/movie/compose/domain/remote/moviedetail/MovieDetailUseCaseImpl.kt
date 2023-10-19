package com.kurio.tetsuya.movie.compose.domain.remote.moviedetail

import com.kurio.tetsuya.movie.compose.data.remote.impl.movie_detail.MovieDetailRepo
import javax.inject.Inject


class MovieDetailUseCaseImpl @Inject constructor(private val movieDetailRepo: MovieDetailRepo) :
    MovieDetailUseCase {
    override fun getMovieDetail(movieId: Int) =
        movieDetailRepo.getMovieDetail(movieId = movieId)
}