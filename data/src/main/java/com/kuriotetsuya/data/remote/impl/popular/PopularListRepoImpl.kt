package com.kuriotetsuya.data.remote.impl.popular

import com.kurio.tetsuya.movie.compose.presentation.com.example.domain.ViewState
import com.kuriotetsuya.data.network.safeApiCall
import com.kuriotetsuya.data.remote.datasource.MovieDataSourceImpl
import com.kuriotetsuya.data.remote.mapper.PopularMapper
import com.kuriotetsuya.domain.popular.FetchPopularRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PopularListRepoImpl @Inject constructor(
    private val movieDetailDataSourceImpl: MovieDataSourceImpl,
    private val popularMapper: PopularMapper,
) : FetchPopularRepo {
    override fun getPopularList() =
        flow {
            emit(ViewState.Loading)
            val response = safeApiCall {
                movieDetailDataSourceImpl.fetchPopularList()
            }

            when (response) {
                is ViewState.Success -> {
                    response.successData?.let { data ->
                        if (data.results.isNotEmpty()) {
                            data.results.map {
                                val mapResponse = popularMapper.mapFromResponse(data)
//                                insertPopularListUseCaseImpl.insertPopularList(mapResponse.movieList)
                            }
                        }
                    } ?: run {
                        emit(ViewState.Error("Error"))
                    }
                }

                is ViewState.Error -> emit(ViewState.Error(response.message))
                is ViewState.ResourceNotFound -> emit(ViewState.ResourceNotFound)
                is ViewState.ServerError -> emit(ViewState.ServerError)
                is ViewState.NetworkError -> emit(ViewState.NetworkError)
                is ViewState.Unauthorized -> emit(ViewState.Unauthorized(response.message))
                else -> emit(ViewState.ResourceNotFound)
            }
        }
}