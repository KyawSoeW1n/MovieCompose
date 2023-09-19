package com.kurio.tetsuya.movie.compose.ui.features.movedetail.viewmodel

import androidx.lifecycle.viewModelScope
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.MovieDetailVO
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.RelatedMovieVO
import com.kurio.tetsuya.movie.compose.domain.remote.moviedetail.MovieDetailUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.remote.related_movie.RelatedMovieUseCaseImpl
import com.kurio.tetsuya.movie.compose.presentation.BaseViewModel
import com.kurio.tetsuya.movie.compose.presentation.ViewState
import com.kurio.tetsuya.movie.compose.util.CoroutinesDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCaseImpl: MovieDetailUseCaseImpl,
    private val relatedMovieUseCaseImpl: RelatedMovieUseCaseImpl,
    private val coroutinesDispatchers: CoroutinesDispatchers,
) : BaseViewModel() {
    private val _relatedMovieStateFlow =
        MutableStateFlow<ViewState<List<RelatedMovieVO>>>(ViewState.Loading)

    private val _movieDetailStateFlow =
        MutableStateFlow<ViewState<MovieDetailVO>>(ViewState.Loading)

    val movieDetailStateFlow: StateFlow<ViewState<MovieDetailVO>> =
        _movieDetailStateFlow.asStateFlow()

    val relatedMovieStateFlow: StateFlow<ViewState<List<RelatedMovieVO>>> =
        _relatedMovieStateFlow.asStateFlow()

    fun changeMovieId(movieId: Int) {
        fetchMovieDetail(movieId = movieId)
        fetchRelatedMovie(movieId = movieId)
    }

    private fun fetchMovieDetail(movieId: Int) {
        viewModelScope.launch(coroutinesDispatchers.io) {
            movieDetailUseCaseImpl.getMovieDetail(movieId = movieId).collectLatest {
                _movieDetailStateFlow.value = it
            }
        }
    }

    private fun fetchRelatedMovie(movieId: Int) {
        viewModelScope.launch(coroutinesDispatchers.io) {
            relatedMovieUseCaseImpl.getRelatedMovieList(movieId = movieId).collectLatest {
                _relatedMovieStateFlow.value = it
            }
        }
    }

}