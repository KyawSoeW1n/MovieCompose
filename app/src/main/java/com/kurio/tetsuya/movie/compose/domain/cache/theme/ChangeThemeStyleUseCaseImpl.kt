package com.kurio.tetsuya.movie.compose.domain.cache.theme

import com.kurio.tetsuya.movie.compose.core.theme.AppThemeType
import com.kurio.tetsuya.movie.compose.data.cache.app.AppPreferencesDataStore
import javax.inject.Inject



class ChangeThemeStyleUseCaseRepoImpl @Inject constructor(
    private val appPreferencesDataStore: AppPreferencesDataStore
) : ChangeThemeStyleUseCaseRepo {
    override suspend fun invoke(appThemeType: AppThemeType) =
        appPreferencesDataStore.changeThemeStyle(appThemeType = appThemeType)
}