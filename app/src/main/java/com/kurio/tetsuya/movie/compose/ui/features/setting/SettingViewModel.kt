package com.kurio.tetsuya.movie.compose.ui.features.setting

import androidx.lifecycle.viewModelScope
import com.kurio.tetsuya.movie.compose.core.locale.LanguageType
import com.kurio.tetsuya.movie.compose.core.theme.AppThemeType
import com.kurio.tetsuya.movie.compose.domain.app_data.GetAppDataUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.locale.ChangeLocaleUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.theme.ChangeDynamicColorUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.theme.ChangeThemeStyleUseCaseImpl
import com.kurio.tetsuya.movie.compose.presentation.BaseViewModel
import com.kurio.tetsuya.movie.compose.util.CoroutinesDispatchersImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val changeThemeStyleUseCaseImpl: ChangeThemeStyleUseCaseImpl,
    private val changeDynamicColorUseCaseImpl: ChangeDynamicColorUseCaseImpl,
    private val changeLocaleUseCase: ChangeLocaleUseCaseImpl,
    private val getAppDataUseCaseImpl: GetAppDataUseCaseImpl,
    private val coroutinesDispatchersImpl: CoroutinesDispatchersImpl,
) : BaseViewModel() {
    val themeMode = MutableStateFlow(value = AppThemeType.LIGHT)
    val languageType = MutableStateFlow(value = LanguageType.en)
    val isDynamicColor = MutableStateFlow(value = false)

    init {
        watchAppConfigurationStream()
    }

    private fun watchAppConfigurationStream() {
        viewModelScope.launch(coroutinesDispatchersImpl.io) {
            getAppDataUseCaseImpl.getThemeMode().collectLatest { appConfiguration ->
                isDynamicColor.value = appConfiguration.useDynamicColors
                themeMode.value = appConfiguration.themeStyle
                languageType.value = appConfiguration.languageType
            }
        }
    }

    fun changeThemeStyle(appThemeType: AppThemeType) {
        viewModelScope.launch(coroutinesDispatchersImpl.io) {
            changeThemeStyleUseCaseImpl(appThemeType = appThemeType)
        }
    }

    fun toggleDynamicColor() {
        viewModelScope.launch(coroutinesDispatchersImpl.io) {
            changeDynamicColorUseCaseImpl.toggle()
        }
    }

    fun setDynamicColorCode(dynamicColorName: String) {
        viewModelScope.launch(coroutinesDispatchersImpl.io) {
            changeDynamicColorUseCaseImpl.setDynamicColorCode(dynamicColorName = dynamicColorName)
        }
    }

    fun changeLocale(languageType: LanguageType) {
        viewModelScope.launch(coroutinesDispatchersImpl.io) {
            changeLocaleUseCase(languageType = languageType)
            this@SettingViewModel.languageType.value = languageType
        }
    }
}