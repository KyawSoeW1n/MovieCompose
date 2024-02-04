package com.kuriotetsuya.data.remote.impl.popular

import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.datasource.movie.MovieCacheDataSourceImpl
import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.entity.MovieTableUpdate
import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.entity.PopularMovie
import com.kuriotetsuya.domain.ViewState
import com.kuriotetsuya.data.network.safeApiCall
import com.kuriotetsuya.data.remote.datasource.MovieRemoteDataSourceImpl
import com.kuriotetsuya.data.remote.mapper.PopularMapper
import com.kuriotetsuya.domain.fetch_popular.FetchPopularMovieRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PopularMovieListRepoImpl @Inject constructor(
    private val movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl,
    private val movieCacheDataSourceImpl: MovieCacheDataSourceImpl,
    private val popularMapper: PopularMapper
) : FetchPopularMovieRepo {
    override fun fetchPopularList() =
        flow {
            emit(ViewState.Loading)
            val response = safeApiCall {
                movieRemoteDataSourceImpl.fetchPopularList()
            }
            when (response) {
                is ViewState.Success -> {
                    response.successData?.let { data ->
                        if (data.results.isNotEmpty()) {
                            data.results.map {
                                val mapResponse = popularMapper.mapFromResponse(data)
                                val list = mapResponse.movieList.map {
                                    MovieTableUpdate(
                                        id = it.id,
                                        title = it.title,
                                        image = it.image,
                                        description = it.overview,
                                    )
                                }
                                val popularMovieList = mapResponse.movieList.map {
                                    PopularMovie(it.id)
                                }
                                movieCacheDataSourceImpl.insertMovieList(list)
                                movieCacheDataSourceImpl.insertPopularMovieList(popularMovieList)
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