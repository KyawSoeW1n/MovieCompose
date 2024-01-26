package com.kuriotetsuya.domain.moviedetail

import com.kuriotetsuya.domain.model.MovieDetailVO
import com.kurio.tetsuya.movie.compose.presentation.com.example.domain.ViewState
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepo {
    fun getMovieDetail(movieId: Int): Flow<ViewState<MovieDetailVO>>
}