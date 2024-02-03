package com.kurio.tetsuya.movie.compose.ui.features.upcoming.viewmodel

import androidx.lifecycle.viewModelScope
import com.kurio.tetsuya.movie.compose.presentation.BaseViewModel
import com.kurio.tetsuya.movie.compose.util.CoroutinesDispatchers
import com.kuriotetsuya.domain.fetch_upcoming.FetchUpcomingMovieUseCase
import com.kuriotetsuya.domain.get_upcoming.GetUpcomingMovieUseCase
import com.kuriotetsuya.domain.update_favourite_status.UpdateFavouriteStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UpcomingViewModel @Inject constructor(
    private val fetchUpcomingMovieUseCase: FetchUpcomingMovieUseCase,
    private val getUpcomingMovieUseCase: GetUpcomingMovieUseCase,
    private val updateFavouriteStatusUseCase: UpdateFavouriteStatusUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : BaseViewModel() {

    private val _upcomingEventState = MutableStateFlow<UpcomingEvent>(UpcomingEvent.ResetEvent)
    val upcomingEventState
        get() = _upcomingEventState.asStateFlow()

    private val _keyword = MutableStateFlow("")
    private val keyword
        get() = _keyword.asStateFlow()


    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    fun setKeyword(keyword: String) {
        _keyword.value = keyword
    }

    fun changeUpcomingScreenEvent(event: UpcomingEvent) {
        _upcomingEventState.value = event
    }

    fun getCacheUpcomingList(keyword: String) =
        getUpcomingMovieUseCase.getUpcomingList(keyword).flowOn(coroutinesDispatchers.io)
            .distinctUntilChanged()

    init {
        fetchUpcomingList()
    }

    fun changeFavouriteStatus(id: Int, flag: Boolean) {
        viewModelScope.launch(coroutinesDispatchers.io) {
            updateFavouriteStatusUseCase.updateFavouriteStatus(movieId = id, flag = flag)
        }
    }

    private fun fetchUpcomingList() {
        viewModelScope.launch(coroutinesDispatchers.io) {
            fetchUpcomingMovieUseCase.getUpcomingList().collectLatest {
                _isRefreshing.emit(false)
            }
        }
    }

    fun refresh() {
        viewModelScope.launch(coroutinesDispatchers.io) {
            _isRefreshing.emit(true)
            delay(1500)
            fetchUpcomingList()
        }
    }
}