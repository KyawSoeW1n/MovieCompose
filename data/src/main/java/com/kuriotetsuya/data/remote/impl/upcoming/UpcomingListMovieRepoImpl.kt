package com.kuriotetsuya.data.remote.impl.upcoming

import com.kuriotetsuya.domain.fetch_upcoming.FetchUpcomingMovieRepo
import com.kurio.tetsuya.movie.compose.presentation.com.example.domain.ViewState
import com.kuriotetsuya.data.cache.entity.CacheMovie
import com.kuriotetsuya.data.network.safeApiCall
import com.kuriotetsuya.data.remote.datasource.MovieDataSourceImpl
import com.kuriotetsuya.data.remote.mapper.UpcomingMapper
import com.kuriotetsuya.domain.model.UpcomingMovieListVO
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpcomingListMovieRepoImpl @Inject constructor(
    private val movieDataSourceImpl: MovieDataSourceImpl,
    private val upcomingMapper: UpcomingMapper,
) : FetchUpcomingMovieRepo {
    override fun fetchUpcomingList() =
        flow {
            emit(ViewState.Loading)
            val response = safeApiCall {
                movieDataSourceImpl.fetchUpcomingList()
            }
            when (response) {
                is ViewState.Success -> {
                    response.successData?.let { data ->
                        if (data.results.isNotEmpty()) {
                            data.results.map {
                                val mapResponse = upcomingMapper.mapFromResponse(data)
                                val list = mapResponse.movieList.map {
                                    CacheMovie(
                                        id = it.id,
                                        title = it.title,
                                        image = it.image,
                                        description = it.overview,
                                        isFavourite = it.isFavourite
                                    )
                                }
                                movieDataSourceImpl.insertUpcomingList(list)
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