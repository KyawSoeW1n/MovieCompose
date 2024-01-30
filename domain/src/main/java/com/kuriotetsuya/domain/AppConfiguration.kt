package com.kuriotetsuya.domain

data class AppConfiguration(
    val useDynamicColors: Boolean,
    val themeStyle: AppThemeType,
    val languageType: LanguageType,
    val dynamicColorCode: String,
)