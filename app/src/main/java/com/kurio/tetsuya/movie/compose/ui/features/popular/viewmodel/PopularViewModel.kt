package com.kurio.tetsuya.movie.compose.ui.features.popular.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kurio.tetsuya.movie.compose.data.remote.impl.popular.PopularListRepoImpl
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.PopularMovieListVO
import com.kurio.tetsuya.movie.compose.domain.cache.popular.GetCachePopularListUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.popular.UpdateCachePopularMovieRepoImpl
import com.kurio.tetsuya.movie.compose.presentation.BaseViewModel
import com.kurio.tetsuya.movie.compose.presentation.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularListUseCaseImpl: PopularListRepoImpl,
    private val getCachePopularListUseCaseImpl: GetCachePopularListUseCaseImpl,
    private val updateCachePopularMovieRepoImpl: UpdateCachePopularMovieRepoImpl,
) : BaseViewModel() {
    private val popularListLiveData: MutableLiveData<ViewState<PopularMovieListVO>> by lazy {
        MutableLiveData()
    }

    fun getCachePopularList() = getCachePopularListUseCaseImpl.getCachePopularList().flowOn(Dispatchers.IO)

    init {
        fetchCarList()
    }


    fun changeFavouriteStatus(id: Int, flag: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            updateCachePopularMovieRepoImpl.updateCachePopularMovie(id = id, flag = flag)
        }
    }

    private fun fetchCarList() {
        viewModelScope.launch(Dispatchers.IO) {
            popularListUseCaseImpl.getPopularList().collectLatest {
                popularListLiveData.postValue(it)
            }
        }
    }
}