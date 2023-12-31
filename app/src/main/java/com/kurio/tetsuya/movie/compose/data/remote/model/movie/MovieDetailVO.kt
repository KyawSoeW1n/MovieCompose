package com.kurio.tetsuya.movie.compose.data.remote.model.movie

import androidx.compose.runtime.Immutable

@Immutable
data class MovieDetailVO(
    val id: Int,
    val name: String,
    val overview: String,
    val genres: String,
    val rating: String,
)