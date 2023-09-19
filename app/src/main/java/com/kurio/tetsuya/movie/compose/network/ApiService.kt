package com.kurio.tetsuya.movie.compose.network

import com.kurio.tetsuya.movie.compose.network.response.movie_detail.MovieDetailResponse
import com.kurio.tetsuya.movie.compose.network.response.popular.PopularResponse
import com.kurio.tetsuya.movie.compose.network.response.related_movie.RelatedMovieResponse
import com.kurio.tetsuya.movie.compose.network.response.upcoming.UpcomingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("popular")
    fun getPopularList(): Call<PopularResponse>

    @GET("upcoming")
    fun getUpcoming(): Call<UpcomingResponse>

    @GET("{movieId}")
    fun getMovieDetail(@Path("movieId") id: Int): Call<MovieDetailResponse>

    @GET("{movieId}/similar")
    fun getRelatedMovie(@Path("movieId") id: Int): Call<RelatedMovieResponse>
}