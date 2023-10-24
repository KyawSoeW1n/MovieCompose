package com.kurio.tetsuya.movie.compose.domain.app_data

import com.kurio.tetsuya.movie.compose.domain.model.AppConfiguration
import kotlinx.coroutines.flow.Flow


interface GetAppDataUseCase {
    suspend fun getThemeMode(): Flow<AppConfiguration>
}