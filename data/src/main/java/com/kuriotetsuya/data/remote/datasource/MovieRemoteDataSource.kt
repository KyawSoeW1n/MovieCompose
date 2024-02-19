package com.kuriotetsuya.data.remote.datasource

import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.network.response.upcoming.UpcomingResponse
import com.kurio.tetsuya.movie.compose.network.response.movie_detail.MovieDetailResponse
import com.kurio.tetsuya.movie.compose.network.response.popular.PopularResponse
import com.kurio.tetsuya.movie.compose.network.response.related_movie.RelatedMovieResponse

interface MovieRemoteDataSource {
    fun fetchUpcomingList(): UpcomingResponse
    fun fetchPopularList(): PopularResponse
    fun fetchRelatedMovie(movieId: Int): RelatedMovieResponse

    fun fetchMovieDetail(movieId: Int): MovieDetailResponse
}