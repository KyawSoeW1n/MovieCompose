package com.kurio.tetsuya.movie.compose.data.remote.impl.movie_detail

import android.content.Context
import com.kurio.tetsuya.movie.compose.core.UseCaseState
import com.kurio.tetsuya.movie.compose.data.remote.datasource.MovieDataSourceImpl
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.MovieDetailVO
import com.kurio.tetsuya.movie.compose.extensions.isOnline
import com.kurio.tetsuya.movie.compose.network.safeApiCall
import com.kurio.tetsuya.movie.compose.presentation.ViewState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import java.text.DecimalFormat
import javax.inject.Inject

class MovieDetailRepoImpl @Inject constructor(
    private val movieDetailDataSourceImpl: MovieDataSourceImpl,
    @ApplicationContext private val context: Context,
) : MovieDetailRepo {
    override fun getMovieDetail(movieId: Int) =
        flow {
            emit(ViewState.Loading)
            if (context.isOnline()) {
                val response = safeApiCall {
                    movieDetailDataSourceImpl.getMovieDetail(movieId = movieId)
                }
                when (response) {
                    is UseCaseState.Success -> {
                        val decimalFormat = DecimalFormat("#.00")
                        response.successData?.let { data ->
                            emit(
                                ViewState.Success(
                                    MovieDetailVO(
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