package com.kurio.tetsuya.movie.compose.data.remote.impl.upcoming

import com.kurio.tetsuya.movie.compose.data.remote.model.movie.PopularMovieListVO
import com.kurio.tetsuya.movie.compose.presentation.ViewState
import kotlinx.coroutines.flow.Flow

interface UpcomingListRepo {
    fun getUpcomingList(): Flow<ViewState<PopularMovieListVO>>
}