package com.kuriotetsuya.domain.related_movie

import com.kuriotetsuya.domain.ViewState
import com.kuriotetsuya.domain.model.RelatedMovieVO
import kotlinx.coroutines.flow.Flow

interface RelatedMovieRepo {
    fun getRelatedMovie(movieId: Int): Flow<ViewState<List<RelatedMovieVO>>>
}