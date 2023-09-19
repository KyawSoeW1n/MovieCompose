package com.kurio.tetsuya.movie.compose.domain.remote.fetch_popular

import com.kurio.tetsuya.movie.compose.data.remote.model.movie.PopularMovieListVO
import com.kurio.tetsuya.movie.compose.presentation.ViewState
import kotlinx.coroutines.flow.Flow


interface PopularListUseCase {
    suspend fun getPopularList(): Flow<ViewState<PopularMovieListVO>>
}