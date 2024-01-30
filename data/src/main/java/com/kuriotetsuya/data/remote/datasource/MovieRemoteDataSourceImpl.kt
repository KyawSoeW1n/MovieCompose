package com.kuriotetsuya.data.remote.datasource

import com.kurio.tetsuya.movie.compose.network.ApiService
import com.kuriotetsuya.data.network.executeOrThrow
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) :
    MovieRemoteDataSource {
    override fun fetchUpcomingList() = apiService.getUpcoming().executeOrThrow()
    override fun fetchPopularList() = apiService.getPopularList().executeOrThrow()
    override fun fetchRelatedMovie(movieId: Int) =
        apiService.getRelatedMovie(movieId).executeOrThrow()

    override fun fetchMovieDetail(movieId: Int) =
        apiService.getMovieDetail(movieId).executeOrThrow()
}