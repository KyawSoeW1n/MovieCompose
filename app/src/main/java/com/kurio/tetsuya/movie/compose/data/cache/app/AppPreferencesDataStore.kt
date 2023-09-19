package com.kurio.tetsuya.movie.compose.data.cache.app

import com.kurio.tetsuya.movie.compose.core.theme.AppThemeType
import com.kurio.tetsuya.movie.compose.domain.model.AppConfiguration
import kotlinx.coroutines.flow.Flow

interface AppPreferencesDataStore {
    val appConfigurationStream: Flow<AppConfiguration>

    suspend fun toggleDynamicColors()

    suspend fun changeThemeStyle(appThemeType: AppThemeType)
}