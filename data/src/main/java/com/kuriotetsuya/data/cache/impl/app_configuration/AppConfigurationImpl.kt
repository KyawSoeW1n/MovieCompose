package com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.impl.app_configuration

import com.kurio.tetsuya.movie.compose.core.com.kuriotetsuya.data.cache.datasource.app_configuration.AppConfigurationDataSourceImpl
import com.kuriotetsuya.domain.AppThemeType
import com.kuriotetsuya.domain.LanguageType
import com.kuriotetsuya.domain.theme.AppConfigurationRepo
import javax.inject.Inject

class AppConfigurationImpl @Inject constructor(
    private val appConfigurationDataSourceImpl: AppConfigurationDataSourceImpl,
) : AppConfigurationRepo {
    override suspend fun changeLanguage(languageType: LanguageType) =
        appConfigurationDataSourceImpl.changeLanguage(languageType)

    override suspend fun getAppConfiguration() =
        appConfigurationDataSourceImpl.appConfigurationStream

    override suspend fun toggleMode() = appConfigurationDataSourceImpl.toggleMode()

    override suspend fun setDynamicColorCode(dynamicColorName: String) =
        appConfigurationDataSourceImpl.setDynamicColorCode(dynamicColorName)

    override suspend fun changeAppTheme(appThemeType: AppThemeType) =
        appConfigurationDataSourceImpl.changeAppTheme(appThemeType)

}