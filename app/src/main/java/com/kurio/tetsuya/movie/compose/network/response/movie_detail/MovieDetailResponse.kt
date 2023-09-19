package com.kurio.tetsuya.movie.compose.network.response.movie_detail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailResponse(
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val overview: String,
    val popularity: Double,
    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompany>,
    @Json(name = "production_countries")
    val productionCountries: List<ProductionCountry>,
    @Json(name = "release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    @Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int
)

@JsonClass(generateAdapter = true)
data class Genre(
    val id: Int,
    val name: String
)

@JsonClass(generateAdapter = true)
data class ProductionCompany(
    val id: Int,
    @Json(name = "logo_path")
    val logoPath: String?,
    val name: String,
    @Json(name = "origin_country")
    val originCountry: String
)

@JsonClass(generateAdapter = true)
data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

@JsonClass(generateAdapter = true)
data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)