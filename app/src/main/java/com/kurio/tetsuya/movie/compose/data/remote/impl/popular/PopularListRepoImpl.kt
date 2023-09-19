package com.kurio.tetsuya.movie.compose.data.remote.impl.popular

import android.content.Context
import com.kurio.tetsuya.movie.compose.core.UseCaseState
import com.kurio.tetsuya.movie.compose.data.remote.datasource.MovieDataSourceImpl
import com.kurio.tetsuya.movie.compose.data.remote.mapper.PopularMapper
import com.kurio.tetsuya.movie.compose.domain.cache.popular.InsertPopularListUseCaseImpl
import com.kurio.tetsuya.movie.compose.extensions.isOnline
import com.kurio.tetsuya.movie.compose.network.safeApiCall
import com.kurio.tetsuya.movie.compose.presentation.ViewState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PopularListRepoImpl @Inject constructor(
    private val movieDetailDataSourceImpl: MovieDataSourceImpl,
    private val popularMapper: PopularMapper,
    private val insertPopularListUseCaseImpl: InsertPopularListUseCaseImpl,
    @ApplicationContext private val context: Context,
) : PopularListRepo {
    override fun getPopularList() =
        flow {
            emit(ViewState.Loading)
            if (context.isOnline()) {
                val response = safeApiCall {
                    movieDetailDataSourceImpl.getPopularList()
                }

                when (response) {
                    is UseCaseState.Success -> {
                        response.successData?.let { data ->
                            if (data.results.isNotEmpty()) {
                                data.results.map {
                                    val mapResponse = popularMapper.mapFromResponse(data)
                                    insertPopularListUseCaseImpl.insertPopularList(mapResponse.movieList)
                                }
                            }
                        } ?: run {
                            emit(ViewState.Error("Error"))
                        }
                    }

                    is UseCaseState.Error -> emit(ViewState.Error(response.message))
                    is UseCaseState.ResourceNotFound -> emit(ViewState.ResourceNotFound)
                    is UseCaseState.ServerError -> emit(ViewState.ServerError)
                    is UseCaseState.NetworkError -> emit(ViewState.NetworkError)
                    is UseCaseState.Unauthorized -> emit(ViewState.Unauthorized(response.message))
                    else -> emit(ViewState.ResourceNotFound)
                }
            } else {
                emit(ViewState.NetworkError)
            }
        }
}