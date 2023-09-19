package com.kurio.tetsuya.movie.compose.data.remote.impl.related_movie

import com.kurio.tetsuya.movie.compose.data.remote.model.movie.RelatedMovieVO
import com.kurio.tetsuya.movie.compose.presentation.ViewState
import kotlinx.coroutines.flow.Flow

interface RelatedMovieRepo {
    fun getRelatedMovie(movieId: Int): Flow<ViewState<List<RelatedMovieVO>>>
}