package com.kurio.tetsuya.movie.compose.data.remote.model.movie

data class MovieItemVO(
    val id: Int,
    val title: String,
    val image: String,
    val overview: String,
    val isFavourite: Boolean = false,
)