package com.kurio.tetsuya.movie.compose.data.remote.impl.upcoming

import android.content.Context
import com.kurio.tetsuya.movie.compose.core.UseCaseState
import com.kurio.tetsuya.movie.compose.data.remote.datasource.MovieDataSourceImpl
import com.kurio.tetsuya.movie.compose.data.remote.mapper.UpcomingMapper
import com.kurio.tetsuya.movie.compose.domain.cache.upcoming.InsertUpcomingListUseCaseImpl
import com.kurio.tetsuya.movie.compose.extensions.isOnline
import com.kurio.tetsuya.movie.compose.extensions.showLog
import com.kurio.tetsuya.movie.compose.network.safeApiCall
import com.kurio.tetsuya.movie.compose.presentation.ViewState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpcomingListRepoImpl @Inject constructor(
    private val movieDataSourceImpl: MovieDataSourceImpl,
    private val upcomingMapper: UpcomingMapper,
    private val insertUpcomingListUseCaseImpl: InsertUpcomingListUseCaseImpl,
    @ApplicationContext private val context: Context,
) : UpcomingListRepo {
    override fun getUpcomingList() =
        flow {
            emit(ViewState.Loading)
            if (context.isOnline()) {
                val response = safeApiCall {
                    movieDataSourceImpl.getUpcomingList()
                }
                when (response) {
                    is UseCaseState.Success -> {
                        response.successData?.let { data ->
                            if (data.results.isNotEmpty()) {
                                data.results.map {
                                    val mapResponse = upcomingMapper.mapFromResponse(data)
                                    insertUpcomingListUseCaseImpl.insertUpcomingList(mapResponse.movieList)
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