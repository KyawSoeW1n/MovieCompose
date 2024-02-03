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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val appConfigurationUseCase: AppConfigurationUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers,

    ) : BaseViewModel() {
    private val _settingVM = MutableStateFlow(
        value = SettingVM(
            themeMode = AppThemeType.LIGHT,
            isDynamicColor = false,
            dynamicColorName = "",
            languateType = LanguageType.en
        )
    )
    val settingUIState = _settingVM.asStateFlow()

    fun watchAppConfigurationStream() {
        viewModelScope.launch(coroutinesDispatchers.io) {
            appConfigurationUseCase.getAppConfiguration().collectLatest { appConfiguration ->
                _settingVM.update {
                    it.copy(
                        themeMode = appConfiguration.themeStyle,
                        isDynamicColor = appConfiguration.useDynamicColors,
                        dynamicColorName = appConfiguration.dynamicColorCode
                    )
                }
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
            _settingVM.update {
                it.copy(
                    languateType = languageType
                )
            }
        }
    }
}

data class SettingVM(
    val themeMode: AppThemeType,
    val isDynamicColor: Boolean,
    val dynamicColorName: String,
    val languateType: LanguageType,
)