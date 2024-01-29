package com.kuriotetsuya.data.remote.impl.upcoming

import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.datasource.movie.MovieCacheDataSourceImpl
import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.entity.PopularMovie
import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.entity.UpcomingMovie
import com.kurio.tetsuya.movie.compose.presentation.com.example.domain.ViewState
import com.kuriotetsuya.data.cache.entity.MovieTable
import com.kuriotetsuya.data.network.safeApiCall
import com.kuriotetsuya.data.remote.datasource.MovieRemoteDataSourceImpl
import com.kuriotetsuya.data.remote.mapper.UpcomingMapper
import com.kuriotetsuya.domain.fetch_upcoming.FetchUpcomingMovieRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpcomingMovieListRepoImpl @Inject constructor(
    private val movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl,
    private val movieCacheDataSourceImpl: MovieCacheDataSourceImpl,
    private val upcomingMapper: UpcomingMapper,
) : FetchUpcomingMovieRepo {
    override fun fetchUpcomingList() =
        flow {
            emit(ViewState.Loading)
            val response = safeApiCall {
                movieRemoteDataSourceImpl.fetchUpcomingList()
            }
            when (response) {
                is ViewState.Success -> {
                    response.successData?.let { data ->
                        if (data.results.isNotEmpty()) {
                            data.results.map {
                                val mapResponse = upcomingMapper.mapFromResponse(data)
                                val list = mapResponse.movieList.map {
                                    MovieTable(
                                        id = it.id,
                                        title = it.title,
                                        image = it.image,
                                        description = it.overview,
                                        isFavourite = it.isFavourite
                                    )
                                }
                                val upcomingMovieList = mapResponse.movieList.map {
                                    UpcomingMovie(it.id)
                                }
                                movieCacheDataSourceImpl.insertMovieList(list)
                                movieCacheDataSourceImpl.insertUpcomingMovieList(upcomingMovieList)

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