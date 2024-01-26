//package com.kurio.tetsuya.movie.compose.domain.cache.theme
//
//import com.kurio.tetsuya.movie.compose.data.cache.app.AppPreferencesDataStore
//import javax.inject.Inject
//
//
//class ChangeDynamicColorUseCaseImpl @Inject constructor(
//    private val appPreferencesDataStore: AppPreferencesDataStore
//) : ChangeDynamicColorUseCase {
//    override suspend fun toggle() =
//        appPreferencesDataStore.toggleDynamicColors()
//    override suspend fun setDynamicColorCode(dynamicColorName: String) =
//        appPreferencesDataStore.setDynamicColorsCode(dynamicColorName = dynamicColorName)
//
//}