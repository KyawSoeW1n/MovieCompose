package com.kuriotetsuya.domain.moviedetail

import com.kuriotetsuya.domain.ViewState
import com.kuriotetsuya.domain.model.MovieDetailVO
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepo {
    fun getMovieDetail(movieId: Int): Flow<ViewState<MovieDetailVO>>
}