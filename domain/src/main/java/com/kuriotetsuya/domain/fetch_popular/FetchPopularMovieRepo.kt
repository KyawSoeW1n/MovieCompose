package com.kuriotetsuya.domain.fetch_popular

import com.kuriotetsuya.domain.ViewState
import com.kuriotetsuya.domain.model.PopularMovieListVO
import kotlinx.coroutines.flow.Flow


interface FetchPopularMovieRepo {
    fun fetchPopularList(): Flow<ViewState<PopularMovieListVO>>
}

