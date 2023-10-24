package com.kurio.tetsuya.movie.compose.data.cache.app

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.kurio.tetsuya.movie.compose.core.locale.LanguageType
import com.kurio.tetsuya.movie.compose.core.theme.AppThemeType
import com.kurio.tetsuya.movie.compose.domain.model.AppConfiguration
import com.kurio.tetsuya.movie.compose.extensions.showLog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppPreferencesDataStoreDataStoreImpl @Inject constructor(
    private val dataStorePreferences: DataStore<Preferences>
) : AppPreferencesDataStore {
    private val tag = this::class.java.simpleName

    override val appConfigurationStream: Flow<AppConfiguration> = dataStorePreferences.data
        .catch { exception ->
            exception.localizedMessage?.let {
                showLog("$tag \t $it")
            }
            emit(value = emptyPreferences())
        }
        .map { preferences ->
            val useDynamicColors = preferences[PreferencesKeys.useDynamicColors] ?: false
            val themeStyle = preferences[PreferencesKeys.themeStyle].toThemeStyleType()
            val locale = preferences[PreferencesKeys.locale].toLocale()
            val dynamicColorCode = preferences[PreferencesKeys.dynamicColorCode]

            AppConfiguration(
                useDynamicColors = useDynamicColors,
                themeStyle = themeStyle,
                languageType = locale,
                dynamicColorCode = dynamicColorCode ?: ""
            )
        }

    override suspend fun toggleDynamicColors() {
        tryIt {
            dataStorePreferences.edit { preferences ->
                val current = preferences[PreferencesKeys.useDynamicColors] ?: false
                preferences[PreferencesKeys.useDynamicColors] = !current
            }
        }
    }

    override suspend fun setDynamicColorsCode(dynamicColorName: String) {
        tryIt {
            dataStorePreferences.edit { preferences ->
                preferences[PreferencesKeys.dynamicColorCode] = dynamicColorName
            }
        }
    }

    override suspend fun changeThemeStyle(appThemeType: AppThemeType) {
        tryIt {
            dataStorePreferences.edit { preferences ->
                preferences[PreferencesKeys.themeStyle] = appThemeType.name
            }
        }
    }

    override suspend fun changeLocale(languageType: LanguageType) {
        tryIt {
            dataStorePreferences.edit { preferences ->
                preferences[PreferencesKeys.locale] = languageType.name
            }
        }
    }

    private suspend fun tryIt(action: suspend () -> Unit) {
        try {
            action()
        } catch (exception: Exception) {
            exception.localizedMessage?.let {
                showLog("$tag \t $it")
            }
        }
    }

    private fun String?.toThemeStyleType(): AppThemeType = when (this) {
        AppThemeType.LIGHT.name -> AppThemeType.LIGHT
        AppThemeType.DARK.name -> AppThemeType.DARK
        AppThemeType.SYSTEM.name -> AppThemeType.SYSTEM
        AppThemeType.DYNAMIC.name -> AppThemeType.DYNAMIC
        else -> AppThemeType.SYSTEM
    }

    private fun String?.toLocale(): LanguageType = when (this) {
        LanguageType.en.name -> LanguageType.en
        LanguageType.my.name -> LanguageType.my
        else -> LanguageType.en
    }

    private object PreferencesKeys {
        val themeStyle = stringPreferencesKey(name = "theme_style")
        val locale = stringPreferencesKey(name = "locale")
        val dynamicColorCode = stringPreferencesKey(name = "dynamic_color_code")
        val useDynamicColors = booleanPreferencesKey(name = "use_dynamic_colors")
    }
}
