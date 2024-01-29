package com.kurio.tetsuya.movie.compose.ui.features.setting

import androidx.lifecycle.viewModelScope
import com.kurio.tetsuya.movie.compose.presentation.BaseViewModel
import com.kurio.tetsuya.movie.compose.util.CoroutinesDispatchers
import com.kuriotetsuya.domain.AppThemeType
import com.kuriotetsuya.domain.LanguageType
import com.kuriotetsuya.domain.theme.AppConfigurationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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
    val themeMode = MutableStateFlow(value = AppThemeType.LIGHT)
    val languageType = MutableStateFlow(value = LanguageType.en)
    val isDynamicColor = MutableStateFlow(value = false)

    init {
        watchAppConfigurationStream()
    }

    private fun watchAppConfigurationStream() {
        viewModelScope.launch(coroutinesDispatchers.io) {
            appConfigurationUseCase.getAppConfiguration().collectLatest { appConfiguration ->
                isDynamicColor.value = appConfiguration.useDynamicColors
                themeMode.value = appConfiguration.themeStyle
                languageType.value = appConfiguration.languageType
            }
        }
    }

    fun changeThemeStyle(appThemeType: AppThemeType) {
        viewModelScope.launch(coroutinesDispatchers.io) {
            appConfigurationUseCase.changeAppTheme(appThemeType = appThemeType)
        }
    }


    fun toggleDynamicColor() {
        viewModelScope.launch(coroutinesDispatchers.io) {
            appConfigurationUseCase.toggleMode()
        }
    }

    fun setDynamicColorCode(dynamicColorName: String) {
        viewModelScope.launch(coroutinesDispatchers.io) {
            appConfigurationUseCase.setDynamicColorCode(dynamicColorName = dynamicColorName)
        }
    }
    fun changeLocale(languageType: LanguageType) {
        viewModelScope.launch(coroutinesDispatchers.io) {
            appConfigurationUseCase.changeLanguage(languageType = languageType)
            this@SettingViewModel.languageType.value = languageType
        }
    }
}