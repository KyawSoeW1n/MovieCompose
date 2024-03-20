package com.kuriotetsuya.data.remote.impl.related_movie

import com.kuriotetsuya.data.convertNetworkString
import com.kuriotetsuya.data.network.safeApiCall
import com.kuriotetsuya.data.remote.datasource.MovieRemoteDataSourceImpl
import com.kuriotetsuya.domain.ViewState
import com.kuriotetsuya.domain.model.RelatedMovieVO
import com.kuriotetsuya.domain.related_movie.RelatedMovieRepo
import kotlinx.coroutines.flow.flow
import java.text.DecimalFormat
import javax.inject.Inject

class RelatedMovieRepoImpl @Inject constructor(
    private val movieDetailDataSourceImpl: MovieRemoteDataSourceImpl,
) : RelatedMovieRepo {
    override fun getRelatedMovie(movieId: Int) =
        flow {
            emit(ViewState.Loading)
            val response = safeApiCall {
                movieDetailDataSourceImpl.fetchRelatedMovie(movieId = movieId)
            }
            when (response) {
                is ViewState.Success -> {

                    val decimalFormat = DecimalFormat("#.00")
                    response.successData?.let { data ->
                        data.results?.let { list ->
                            emit(
                                ViewState.Success(
                                    list.map { item ->
                                        RelatedMovieVO(
                                            id = item.id,
                                            name = item.title,
                                            rating = decimalFormat.format(item.voteAverage),
                                            image = item.posterPath?.convertNetworkString()
                                                ?: "",
                                        )
                                    }.toList()
                                )
                            )
                        } ?: run {
                            emit(ViewState.Error("Error"))
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