package com.kurio.tetsuya.movie.compose.domain.cache.theme

import com.kurio.tetsuya.movie.compose.data.cache.app.AppPreferencesDataStore
import com.kurio.tetsuya.movie.compose.domain.model.AppConfiguration
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ThemeUseCaseImpl @Inject constructor(
    private val appPreferencesDataStore: AppPreferencesDataStore
) : ThemeUseCase {
    override suspend fun invoke(): Flow<AppConfiguration> =
        appPreferencesDataStore.appConfigurationStream
}