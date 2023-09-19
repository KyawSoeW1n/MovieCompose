package com.kurio.tetsuya.movie.compose.domain.remote.fetch_upcoming

import com.kurio.tetsuya.movie.compose.data.remote.model.movie.UpcomingMovieListVO
import com.kurio.tetsuya.movie.compose.presentation.ViewState
import kotlinx.coroutines.flow.Flow


interface UpcomingListUseCase {
    fun getUpcomingList(): Flow<ViewState<UpcomingMovieListVO>>
}