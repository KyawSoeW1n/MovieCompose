package com.kurio.tetsuya.movie.compose.network

import java.io.IOException

data class NetworkException constructor(
    var errorCode: Int = 0,
    var errorBody: String? = null
) : IOException()