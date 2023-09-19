package com.kurio.tetsuya.movie.compose.data.remote.datasource

import com.kurio.tetsuya.movie.compose.network.response.movie_detail.MovieDetailResponse
import com.kurio.tetsuya.movie.compose.network.response.popular.PopularResponse
import com.kurio.tetsuya.movie.compose.network.response.related_movie.RelatedMovieResponse
import com.kurio.tetsuya.movie.compose.network.response.upcoming.UpcomingResponse

interface MovieDataSource {
    fun getUpcomingList(): UpcomingResponse
    fun getPopularList(): PopularResponse
    fun getRelatedMovie(movieId: Int): RelatedMovieResponse

    fun getMovieDetail(movieId: Int): MovieDetailResponse
}