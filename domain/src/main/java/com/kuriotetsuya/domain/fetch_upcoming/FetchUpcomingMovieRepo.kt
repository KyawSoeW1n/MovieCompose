package com.kuriotetsuya.domain.fetch_upcoming

import com.kuriotetsuya.domain.ViewState
import com.kuriotetsuya.domain.model.UpcomingMovieListVO
import kotlinx.coroutines.flow.Flow


interface FetchUpcomingMovieRepo {
    fun fetchUpcomingList(): Flow<ViewState<UpcomingMovieListVO>>
}