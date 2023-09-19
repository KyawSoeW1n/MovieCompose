package com.kurio.tetsuya.movie.compose.domain.remote.moviedetail

import com.kurio.tetsuya.movie.compose.data.remote.model.movie.MovieDetailVO
import com.kurio.tetsuya.movie.compose.presentation.ViewState
import kotlinx.coroutines.flow.Flow


interface MovieDetailUseCase {
    fun getMovieDetail(movieId: Int): Flow<ViewState<MovieDetailVO>>
}