package com.kuriotetsuya.domain.fetch_popular

import com.kuriotetsuya.domain.model.PopularMovieListVO
import com.kurio.tetsuya.movie.compose.presentation.com.example.domain.ViewState
import kotlinx.coroutines.flow.Flow


interface PopularListUseCase {
    suspend fun getPopularList(): Flow<ViewState<PopularMovieListVO>>
}