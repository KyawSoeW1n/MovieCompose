package com.kurio.tetsuya.movie.compose.domain.cache.theme

import com.kurio.tetsuya.movie.compose.core.theme.AppThemeType

interface ChangeThemeStyleUseCase {
    suspend operator fun invoke(appThemeType: AppThemeType)
}