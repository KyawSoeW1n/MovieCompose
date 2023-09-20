package com.kurio.tetsuya.movie.compose.domain.cache.theme

import com.kurio.tetsuya.movie.compose.core.theme.AppThemeType
import com.kurio.tetsuya.movie.compose.data.cache.app.AppPreferencesDataStore
import javax.inject.Inject



class ChangeThemeStyleUseCaseImpl @Inject constructor(
    private val appPreferencesDataStore: AppPreferencesDataStore
) : ChangeThemeStyleUseCase {
    override suspend fun invoke(appThemeType: AppThemeType) =
        appPreferencesDataStore.changeThemeStyle(appThemeType = appThemeType)
}