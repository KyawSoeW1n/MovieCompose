package com.kurio.tetsuya.movie.compose.ui.features.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.PopularMovieListVO
import com.kurio.tetsuya.movie.compose.domain.cache.upcoming.GetCacheUpcomingListUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.upcoming.UpdateCacheUpcomingMovieUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.remote.fetch_upcoming.UpcomingListUseCaseImpl
import com.kurio.tetsuya.movie.compose.presentation.BaseViewModel
import com.kurio.tetsuya.movie.compose.presentation.ViewState
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
) : BaseViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private val upcomingListLiveData: MutableLiveData<ViewState<PopularMovieListVO>> by lazy {
        MutableLiveData()
    }

    fun getCacheUpcomingList() = getCacheUpcomingListUseCaseImpl.getUpcomingList().flowOn(Dispatchers.IO)

    init {
        fetchUpcomingList()
    }

    fun changeFavouriteStatus(id: Int, flag: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            updateCacheUpcomingMovieRepoImpl.updateCacheUpcomingMovie(id = id, flag = flag)
        }
    }

    private fun fetchUpcomingList() {
        viewModelScope.launch(Dispatchers.IO) {
            upcomingListUseCaseImpl.getUpcomingList().collectLatest {
                upcomingListLiveData.postValue(it)
                _isRefreshing.emit(false)
            }
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            _isRefreshing.emit(true)
            delay(1500)
            fetchUpcomingList()
        }
    }
}