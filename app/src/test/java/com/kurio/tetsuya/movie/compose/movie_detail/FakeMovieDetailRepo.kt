package com.kurio.tetsuya.movie.compose.movie_detail

import com.kuriotetsuya.domain.ViewState
import com.kuriotetsuya.domain.model.MovieDetailVO
import com.kuriotetsuya.domain.moviedetail.MovieDetailRepo
import kotlinx.coroutines.flow.flow

class FakeMovieDetailRepo : MovieDetailRepo {

    private val successResponse = ViewState.Success(
        MovieDetailVO(
            id = 1,
            name = "Hero",
            overview = "Good Hero",
            genres = "Comedy,Action",
            rating = "10"
        )
    )

    override fun getMovieDetail(movieId: Int) = flow {
        emit(successResponse)
    }
}