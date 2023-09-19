package com.kurio.tetsuya.movie.compose.domain.remote.moviedetail

import com.kurio.tetsuya.movie.compose.data.remote.impl.movie_detail.MovieDetailRepoImpl
import javax.inject.Inject


class MovieDetailUseCaseImpl @Inject constructor(private val movieDetailRepoImpl: MovieDetailRepoImpl) :
    MovieDetailUseCase {
    override fun getMovieDetail(movieId: Int) =
        movieDetailRepoImpl.getMovieDetail(movieId = movieId)
}