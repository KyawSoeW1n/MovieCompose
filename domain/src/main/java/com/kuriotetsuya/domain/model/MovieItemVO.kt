package com.kuriotetsuya.domain.model

data class MovieItemVO(
    val id: Int,
    val title: String,
    val image: String,
    val overview: String,
    val isFavourite: Boolean = false,
)