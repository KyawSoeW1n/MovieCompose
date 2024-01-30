package com.kuriotetsuya.domain.model

data class MovieDetailVO(
    val id: Int,
    val name: String,
    val overview: String,
    val genres: String,
    val rating: String,
)