package com.kurio.tetsuya.movie.compose.data.remote.datasource

import com.kurio.tetsuya.movie.compose.network.ApiService
import com.kurio.tetsuya.movie.compose.network.executeOrThrow
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    MovieDataSource {
    override fun getUpcomingList() = apiService.getUpcoming().executeOrThrow()
    override fun getPopularList() = apiService.getPopularList().executeOrThrow()
    override fun getRelatedMovie(movieId: Int) =
        apiService.getRelatedMovie(movieId).executeOrThrow()

    override fun getMovieDetail(movieId: Int) = apiService.getMovieDetail(movieId).executeOrThrow()

}