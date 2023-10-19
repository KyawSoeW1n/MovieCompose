package com.kurio.tetsuya.movie.compose.ui.features.popular.viewmodel

import androidx.lifecycle.viewModelScope
import com.kurio.tetsuya.movie.compose.domain.cache.popular.GetCachePopularListUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.popular.UpdateCachePopularMovieRepoImpl
import com.kurio.tetsuya.movie.compose.domain.remote.fetch_popular.PopularListUseCaseImpl
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
class PopularViewModel @Inject constructor(
    private val popularListUseCaseImpl: PopularListUseCaseImpl,
    private val getCachePopularListUseCaseImpl: GetCachePopularListUseCaseImpl,
    private val updateCachePopularMovieRepoImpl: UpdateCachePopularMovieRepoImpl,
    private val coroutinesDispatchers: CoroutinesDispatchers,
) : BaseViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    fun getCachePopularList() =
        getCachePopularListUseCaseImpl.getCachePopularList().flowOn(Dispatchers.IO)

    init {
        fetchPopularList()
    }


    fun changeFavouriteStatus(id: Int, flag: Boolean) {
        viewModelScope.launch(coroutinesDispatchers.io) {
            updateCachePopularMovieRepoImpl.updateCachePopularMovie(id = id, flag = flag)
        }
    }

    private fun fetchPopularList() {
        viewModelScope.launch(coroutinesDispatchers.io) {
            popularListUseCaseImpl.getPopularList().collectLatest {
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