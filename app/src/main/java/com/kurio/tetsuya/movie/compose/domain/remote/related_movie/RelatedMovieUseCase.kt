package com.kurio.tetsuya.movie.compose.domain.remote.related_movie

import com.kurio.tetsuya.movie.compose.data.remote.model.movie.RelatedMovieVO
import com.kurio.tetsuya.movie.compose.presentation.ViewState
import kotlinx.coroutines.flow.Flow

interface RelatedMovieUseCase {
    suspend fun getRelatedMovieList(movieId: Int): Flow<ViewState<List<RelatedMovieVO>>>
}