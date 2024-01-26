package com.kurio.tetsuya.movie.compose.ui.features.movedetail.viewmodel

import androidx.lifecycle.viewModelScope
import com.kurio.tetsuya.movie.compose.extensions.showLog
import com.kurio.tetsuya.movie.compose.presentation.BaseViewModel
import com.kurio.tetsuya.movie.compose.presentation.com.example.domain.ViewState
import com.kurio.tetsuya.movie.compose.util.CoroutinesDispatchers
import com.kuriotetsuya.domain.model.MovieDetailVO
import com.kuriotetsuya.domain.model.RelatedMovieVO
import com.kuriotetsuya.domain.moviedetail.MovieDetailUseCase
import com.kuriotetsuya.domain.related_movie.RelatedMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase,
    private val relatedMovieUseCase: RelatedMovieUseCase,
//    private val getCachePopularMovieDetailUseCase: GetCachePopularMovieDetailUseCase,
//    private val getCacheUpcomingMovieDetailUseCase: GetCacheUpcomingMovieDetailUseCase,
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

//    fun getMovieDetailStatus(id: Int, isUpcoming: Boolean = false) = if (isUpcoming)
//        getCacheUpcomingMovieDetailUseCase.getMovieFavouriteStatus(id)
//            .flowOn(coroutinesDispatchers.io) else getCachePopularMovieDetailUseCase.getMovieFavouriteStatus(
//        id
//    ).flowOn(coroutinesDispatchers.io)

    fun changeMovieId(movieId: Int) {
        fetchMovieDetail(movieId = movieId)
        fetchRelatedMovie(movieId = movieId)
    }

    private fun fetchMovieDetail(movieId: Int) {
        viewModelScope.launch(coroutinesDispatchers.io) {
            movieDetailUseCase.getMovieDetail(movieId = movieId).collectLatest {
                _movieDetailStateFlow.value = it
            }
        }
    }

    fun changeFavouritePopularStatus(id: Int, flag: Boolean) {
        showLog("FAV POPULAR >>>>>>")
        viewModelScope.launch(coroutinesDispatchers.io) {
//            updateCachePopularMovieRepo.updateCachePopularMovie(id = id, flag = flag)
        }
    }

    fun changeFavouriteUpcomingStatus(id: Int, flag: Boolean) {
        showLog("FAV UPCOMING >>>>>>")
        viewModelScope.launch(coroutinesDispatchers.io) {
//            updateCacheUpcomingMovieRepo.updateCacheUpcomingMovie(id = id, flag = flag)
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