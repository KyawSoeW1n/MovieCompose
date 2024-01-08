package com.kurio.tetsuya.movie.compose.domain.app_data

import com.kurio.tetsuya.movie.compose.data.cache.app.AppPreferencesDataStore
import com.kurio.tetsuya.movie.compose.domain.model.AppConfiguration
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetThemeUseCaseImpl @Inject constructor(
    private val appPreferencesDataStore: AppPreferencesDataStore
) : GetThemeUseCase {
    override suspend fun getThemeMode(): Flow<AppConfiguration> =
        appPreferencesDataStore.appConfigurationStream
}