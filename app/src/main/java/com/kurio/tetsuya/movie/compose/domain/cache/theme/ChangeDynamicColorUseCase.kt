package com.kurio.tetsuya.movie.compose.domain.cache.theme


interface ChangeDynamicColorUseCase {
    suspend fun toggle()
    suspend fun setDynamicColorCode(dynamicColorName: String)
}