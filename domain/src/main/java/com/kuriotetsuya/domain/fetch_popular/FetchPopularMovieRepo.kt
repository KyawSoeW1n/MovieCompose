package com.kuriotetsuya.domain.fetch_popular

import com.kurio.tetsuya.movie.compose.presentation.com.example.domain.ViewState
import com.kuriotetsuya.domain.model.PopularMovieListVO
import kotlinx.coroutines.flow.Flow


interface FetchPopularMovieRepo {
    fun fetchPopularList(): Flow<ViewState<PopularMovieListVO>>
}

