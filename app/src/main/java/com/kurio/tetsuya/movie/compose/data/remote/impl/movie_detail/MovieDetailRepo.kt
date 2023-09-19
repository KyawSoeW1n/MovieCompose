package com.kurio.tetsuya.movie.compose.data.remote.impl.movie_detail

import com.kurio.tetsuya.movie.compose.data.remote.model.movie.MovieDetailVO
import com.kurio.tetsuya.movie.compose.presentation.ViewState
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepo {
    fun getMovieDetail(movieId: Int): Flow<ViewState<MovieDetailVO>>
}