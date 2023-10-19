package com.kurio.tetsuya.movie.compose.ui.features.upcoming.viewmodel

import androidx.lifecycle.viewModelScope
import com.kurio.tetsuya.movie.compose.domain.cache.upcoming.GetCacheUpcomingListUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.upcoming.UpdateCacheUpcomingMovieUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.remote.fetch_upcoming.UpcomingListUseCaseImpl
import com.kurio.tetsuya.movie.compose.presentation.BaseViewModel
import com.kurio.tetsuya.movie.compose.util.CoroutinesDispatchers
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
class UpcomingViewModel @Inject constructor(
    private val upcomingListUseCaseImpl: UpcomingListUseCaseImpl,
    private val getCacheUpcomingListUseCaseImpl: GetCacheUpcomingListUseCaseImpl,
    private val updateCacheUpcomingMovieRepoImpl: UpdateCacheUpcomingMovieUseCaseImpl,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : BaseViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    fun getCacheUpcomingList() =
        getCacheUpcomingListUseCaseImpl.getUpcomingList().flowOn(Dispatchers.IO)

    init {
        fetchUpcomingList()
    }

    fun changeFavouriteStatus(id: Int, flag: Boolean) {
        viewModelScope.launch(coroutinesDispatchers.io) {
            updateCacheUpcomingMovieRepoImpl.updateCacheUpcomingMovie(id = id, flag = flag)
        }
    }

    private fun fetchUpcomingList() {
        viewModelScope.launch(coroutinesDispatchers.io) {
            upcomingListUseCaseImpl.getUpcomingList().collectLatest {
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