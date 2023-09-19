package com.kurio.tetsuya.movie.compose.domain.cache.theme

import com.kurio.tetsuya.movie.compose.domain.model.AppConfiguration
import kotlinx.coroutines.flow.Flow


interface ThemeUseCase {
    suspend operator fun invoke(): Flow<AppConfiguration>
}