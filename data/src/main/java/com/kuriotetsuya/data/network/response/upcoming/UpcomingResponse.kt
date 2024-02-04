package com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.network.response.upcoming

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpcomingResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)

@JsonClass(generateAdapter = true)
data class Dates(
    val maximum: String,
    val minimum: String
)

@JsonClass(generateAdapter = true)
data class Result(
    val adult: Boolean? = false,
    @Json(name = "backdrop_path")
    val backdropPath: String? = "",
    @Json(name = "genre_ids")
    val genreIds: List<Int>? = mutableListOf(),
    val id: Int,
    @Json(name = "original_language")
    val originalLanguage: String? = "",
    @Json(name = "original_title")
    val originalTitle: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    @Json(name = "poster_path")
    val posterPath: String? = "",
    @Json(name = "release_date")
    val releaseDate: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    @Json(name = "vote_average")
    val voteAverage: Double? = 0.0,
    @Json(name = "vote_count")
    val voteCount: Int? = 0
)