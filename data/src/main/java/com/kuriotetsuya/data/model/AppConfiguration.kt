package com.kuriotetsuya.data.model

import com.kuriotetsuya.domain.AppThemeType
import com.kuriotetsuya.domain.LanguageType

data class AppConfiguration(
    val useDynamicColors: Boolean,
    val themeStyle: AppThemeType,
    val languageType: LanguageType,
    val dynamicColorCode: String,
)