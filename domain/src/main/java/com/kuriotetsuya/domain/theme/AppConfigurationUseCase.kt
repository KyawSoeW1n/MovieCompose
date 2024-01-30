package com.kuriotetsuya.domain.theme

import com.kuriotetsuya.domain.AppThemeType
import com.kuriotetsuya.domain.LanguageType
import javax.inject.Inject

class AppConfigurationUseCase @Inject constructor(private val appConfigurationRepo: AppConfigurationRepo) {
    suspend fun changeLanguage(languageType: LanguageType) =
        appConfigurationRepo.changeLanguage(languageType)

    suspend fun getAppConfiguration() = appConfigurationRepo.getAppConfiguration()

    suspend fun changeAppTheme(appThemeType: AppThemeType) =
        appConfigurationRepo.changeAppTheme(appThemeType = appThemeType)

    suspend fun toggleMode() = appConfigurationRepo.toggleMode()

    suspend fun setDynamicColorCode(dynamicColorName: String) =
        appConfigurationRepo.setDynamicColorCode(dynamicColorName)


}