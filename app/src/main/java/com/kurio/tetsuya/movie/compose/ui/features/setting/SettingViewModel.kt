package com.kurio.tetsuya.movie.compose.ui.features.setting

import androidx.lifecycle.viewModelScope
import com.kurio.tetsuya.movie.compose.presentation.BaseViewModel
import com.kurio.tetsuya.movie.compose.util.CoroutinesDispatchers
import com.kuriotetsuya.domain.AppThemeType
import com.kuriotetsuya.domain.LanguageType
import com.kuriotetsuya.domain.theme.AppConfigurationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val appConfigurationUseCase: AppConfigurationUseCase,
//    private val changeDynamicColorUseCase: ChangeDynamicColorUseCase,
//    private val changeLocaleUseCase: ChangeLocaleUseCase,
//    private val getThemeUseCase: GetThemeUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers,
) : BaseViewModel() {
    private val _themeMode = MutableStateFlow(value = AppThemeType.LIGHT)
    val themeMode = _themeMode.asStateFlow()
    private val _isDynamicColor = MutableStateFlow(value = false)
    val isDynamicColor = _isDynamicColor.asStateFlow()

    private val _languageType = MutableStateFlow(value = LanguageType.en)
    val languageType = _languageType.asStateFlow()

    init {
        watchAppConfigurationStream()
    }

    private fun watchAppConfigurationStream() {
        viewModelScope.launch(coroutinesDispatchers.io) {
            appConfigurationUseCase.getAppConfiguration().collectLatest { appConfiguration ->
                _isDynamicColor.value = appConfiguration.useDynamicColors
                _themeMode.value = appConfiguration.themeStyle
                _languageType.value = appConfiguration.languageType
            }
        }
    }

    fun changeThemeStyle(appThemeType: AppThemeType) {
        viewModelScope.launch(coroutinesDispatchers.io) {
            appConfigurationUseCase.changeAppTheme(appThemeType = appThemeType)
        }
    }


    fun toggleDynamicColor() {
        viewModelScope.launch {
            appConfigurationUseCase.toggleMode()
        }
    }

    fun setDynamicColorCode(dynamicColorName: String) {
        viewModelScope.launch {
            appConfigurationUseCase.setDynamicColorCode(dynamicColorName = dynamicColorName)
        }
    }

    fun changeLocale(languageType: LanguageType) {
        viewModelScope.launch {
            appConfigurationUseCase.changeLanguage(languageType = languageType)
            _languageType.value = languageType
        }
    }
}