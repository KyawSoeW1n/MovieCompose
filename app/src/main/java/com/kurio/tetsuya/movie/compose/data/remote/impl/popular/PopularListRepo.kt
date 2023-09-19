package com.kurio.tetsuya.movie.compose.data.remote.impl.popular

import com.kurio.tetsuya.movie.compose.data.remote.model.movie.PopularMovieListVO
import com.kurio.tetsuya.movie.compose.presentation.ViewState
import kotlinx.coroutines.flow.Flow


interface PopularListRepo {
    fun getPopularList(): Flow<ViewState<PopularMovieListVO>>
}