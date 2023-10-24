package com.kurio.tetsuya.movie.compose.domain.model

import com.kurio.tetsuya.movie.compose.core.locale.LanguageType
import com.kurio.tetsuya.movie.compose.core.theme.AppThemeType

data class AppConfiguration(
    val useDynamicColors: Boolean,
    val themeStyle: AppThemeType,
    val languageType: LanguageType,
    val dynamicColorCode: String,
)