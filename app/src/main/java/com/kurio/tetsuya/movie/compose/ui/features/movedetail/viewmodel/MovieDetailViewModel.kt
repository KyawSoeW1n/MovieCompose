package com.kurio.tetsuya.movie.compose.ui.features.movedetail.viewmodel

import androidx.lifecycle.viewModelScope
import com.kurio.tetsuya.movie.compose.presentation.BaseViewModel
import com.kuriotetsuya.domain.ViewState
import com.kurio.tetsuya.movie.compose.util.CoroutinesDispatchers
import com.kuriotetsuya.domain.model.MovieDetailVO
import com.kuriotetsuya.domain.model.RelatedMovieVO
import com.kuriotetsuya.domain.moviedetail.GetCacheMovieDetailUseCase
import com.kuriotetsuya.domain.moviedetail.MovieDetailUseCase
import com.kuriotetsuya.domain.related_movie.RelatedMovieUseCase
import com.kuriotetsuya.domain.update_favourite_status.UpdateFavouriteStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase,
    private val relatedMovieUseCase: RelatedMovieUseCase,
    private val getCacheMovieDetailUseCase: GetCacheMovieDetailUseCase,
    private val updateFavouriteStatusUseCase: UpdateFavouriteStatusUseCase,
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

    fun getMovieDetailFromCache(movieId: Int) =
        getCacheMovieDetailUseCase.getMovieDetail(movieId).flowOn(coroutinesDispatchers.io)

    private fun fetchMovieDetail(movieId: Int) {
        viewModelScope.launch(coroutinesDispatchers.io) {
            movieDetailUseCase.getMovieDetail(movieId = movieId).collectLatest {
                _movieDetailStateFlow.value = it
            }
        }
    }

    fun changeFavouriteStatus(id: Int, flag: Boolean) {
        viewModelScope.launch(coroutinesDispatchers.io) {
            updateFavouriteStatusUseCase.updateFavouriteStatus(movieId = id, flag = flag)
        }
    }

    private fun fetchRelatedMovie(movieId: Int) {
        viewModelScope.launch(coroutinesDispatchers.io) {
            relatedMovieUseCase.getRelatedMovieList(movieId = movieId).collectLatest {
                _relatedMovieStateFlow.value = it
            }
        }
    }

}