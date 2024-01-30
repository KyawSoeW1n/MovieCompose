package com.kurio.tetsuya.movie.compose.network.response.movie_detail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailResponse(
    val adult: Boolean? = null,
    @Json(name = "backdrop_path")
    val backdropPath: String? = null,
    val budget: Int? = null,
    val genres: List<Genre>? = null,
    val homepage: String? = null,
    val id: Int? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompany>? = null,
    @Json(name = "production_countries")
    val productionCountries: List<ProductionCountry>? = null,
    @Json(name = "release_date")
    val releaseDate: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    @Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguage>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    @Json(name = "vote_average")
    val voteAverage: Double? = null,
    @Json(name = "vote_count")
    val voteCount: Int? = null
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