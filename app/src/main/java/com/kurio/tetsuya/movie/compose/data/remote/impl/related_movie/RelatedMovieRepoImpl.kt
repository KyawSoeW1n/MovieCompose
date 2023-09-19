package com.kurio.tetsuya.movie.compose.data.remote.impl.related_movie

import android.content.Context
import com.kurio.tetsuya.movie.compose.core.UseCaseState
import com.kurio.tetsuya.movie.compose.data.remote.datasource.MovieDataSourceImpl
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.RelatedMovieVO
import com.kurio.tetsuya.movie.compose.extensions.convertNetworkString
import com.kurio.tetsuya.movie.compose.extensions.isOnline
import com.kurio.tetsuya.movie.compose.network.safeApiCall
import com.kurio.tetsuya.movie.compose.presentation.ViewState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import java.text.DecimalFormat
import javax.inject.Inject

class RelatedMovieRepoImpl @Inject constructor(
    private val movieDetailDataSourceImpl: MovieDataSourceImpl,
    @ApplicationContext private val context: Context,
) : RelatedMovieRepo {
    override fun getRelatedMovie(movieId: Int) =
        flow {
            emit(ViewState.Loading)
            if (context.isOnline()) {
                val response = safeApiCall {
                    movieDetailDataSourceImpl.getRelatedMovie(movieId = movieId)
                }
                when (response) {
                    is UseCaseState.Success -> {

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