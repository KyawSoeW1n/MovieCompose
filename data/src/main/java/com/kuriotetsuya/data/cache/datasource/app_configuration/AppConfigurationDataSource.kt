package com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.datasource.app_configuration

import com.kuriotetsuya.domain.AppConfiguration
import com.kuriotetsuya.domain.AppThemeType
import com.kuriotetsuya.domain.LanguageType
import kotlinx.coroutines.flow.Flow


interface AppConfigurationDataSource {
    val appConfigurationStream: Flow<AppConfiguration>

    suspend fun changeLanguage(languageType: LanguageType)

    suspend fun getAppConfiguration(): Flow<AppConfiguration>

    suspend fun toggleMode()

    suspend fun setDynamicColorCode(dynamicColorName: String)

    suspend fun changeAppTheme(appThemeType: AppThemeType)
}