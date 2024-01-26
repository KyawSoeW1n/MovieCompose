package com.kuriotetsuya.domain.popular

import com.kuriotetsuya.domain.model.PopularMovieListVO
import com.kurio.tetsuya.movie.compose.presentation.com.example.domain.ViewState
import kotlinx.coroutines.flow.Flow


interface FetchPopularRepo {
    fun getPopularList(): Flow<ViewState<PopularMovieListVO>>
}