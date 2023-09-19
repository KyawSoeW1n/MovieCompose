package com.kurio.tetsuya.movie.compose.extensions

fun String.convertNetworkString() : String {
    return "http://image.tmdb.org/t/p/w500$this"
}