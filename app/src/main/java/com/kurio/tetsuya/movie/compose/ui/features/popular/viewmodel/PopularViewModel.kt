package com.kurio.tetsuya.movie.compose.ui.features.popular.viewmodel

import androidx.lifecycle.viewModelScope
import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.showLog
import com.kurio.tetsuya.movie.compose.presentation.BaseViewModel
import com.kurio.tetsuya.movie.compose.util.CoroutinesDispatchers
import com.kuriotetsuya.domain.fetch_popular.FetchPopularMovieUseCase
import com.kuriotetsuya.domain.get_popular.GetPopularMovieUseCase
import com.kuriotetsuya.domain.update_favourite_status.UpdateFavouriteStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PopularViewModel @Inject constructor(
    private val coroutinesDispatchers: CoroutinesDispatchers,
    private val getPopularMovieUseCase: GetPopularMovieUseCase,
    private val fetchPopularMovieUseCase: FetchPopularMovieUseCase,
    private val updateFavouriteStatusUseCase: UpdateFavouriteStatusUseCase,
) : BaseViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    init {
        fetchPopularList()
    }

    fun getCachePopularList() =
        getPopularMovieUseCase.getPopularList().flowOn(coroutinesDispatchers.io)

    fun changeFavouriteStatus(id: Int, flag: Boolean) {
        viewModelScope.launch(coroutinesDispatchers.io) {
            updateFavouriteStatusUseCase.updateFavouriteStatus(movieId = id, flag = flag)
        }
    }

    private fun fetchPopularList() {
        viewModelScope.launch(coroutinesDispatchers.io) {
            fetchPopularMovieUseCase.fetchPopularList().collectLatest {
                _isRefreshing.emit(false)
            }
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            _isRefreshing.emit(true)
            delay(1500)
            fetchPopularList()
        }
    }
}