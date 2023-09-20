package com.kurio.tetsuya.movie.compose.domain.cache.locale

import com.kurio.tetsuya.movie.compose.core.locale.LanguageType


interface ChangeLocaleUseCase {
    suspend operator fun invoke(languageType: LanguageType)
}