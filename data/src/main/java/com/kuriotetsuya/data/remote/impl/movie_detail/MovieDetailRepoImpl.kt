package com.kuriotetsuya.data.remote.impl.movie_detail

import com.kuriotetsuya.domain.ViewState
import com.kuriotetsuya.data.network.safeApiCall
import com.kuriotetsuya.data.remote.datasource.MovieRemoteDataSourceImpl
import com.kuriotetsuya.domain.model.MovieDetailVO
import com.kuriotetsuya.domain.moviedetail.MovieDetailRepo
import kotlinx.coroutines.flow.flow
import java.text.DecimalFormat
import javax.inject.Inject

class MovieDetailRepoImpl @Inject constructor(
    private val movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl,
) : MovieDetailRepo {
    override fun getMovieDetail(movieId: Int) =
        flow {
            emit(ViewState.Loading)
            val response = safeApiCall {
                movieRemoteDataSourceImpl.fetchMovieDetail(movieId = movieId)
            }
            when (response) {
                is ViewState.Success -> {
                    val decimalFormat = DecimalFormat("#.00")
                    response.successData?.let { data ->
                        emit(
                            ViewState.Success(
                                MovieDetailVO(
                                    id = data.id ?: -1,
                                    name = data.title ?: "",
                                    overview = data.overview ?: "",
                                    genres = data.genres?.joinToString { it.name } ?: "",
                                    rating = decimalFormat.format(data.voteAverage)
                                )
                            )
                        )
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