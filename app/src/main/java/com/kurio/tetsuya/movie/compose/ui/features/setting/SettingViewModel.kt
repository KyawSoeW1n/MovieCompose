package com.kurio.tetsuya.movie.compose.ui.features.setting

import androidx.lifecycle.viewModelScope
import com.kurio.tetsuya.movie.compose.core.locale.LanguageType
import com.kurio.tetsuya.movie.compose.core.theme.AppThemeType
import com.kurio.tetsuya.movie.compose.domain.app_data.GetAppDataUseCaseImpl
import com.kurio.tetsuya.movie.compose.domain.cache.locale.ChangeLocaleUseCase
import com.kurio.tetsuya.movie.compose.domain.cache.theme.ChangeThemeStyleUseCase
import com.kurio.tetsuya.movie.compose.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val changeThemeStyleUseCase: ChangeThemeStyleUseCase,
    private val changeLocaleUseCase: ChangeLocaleUseCase,
    private val getAppDataUseCaseImpl: GetAppDataUseCaseImpl
) : BaseViewModel() {
    val themeMode = MutableStateFlow(value = AppThemeType.LIGHT)
    val languageType = MutableStateFlow(value = LanguageType.en)

    init {
        watchAppConfigurationStream()
    }

    private fun watchAppConfigurationStream() {
        viewModelScope.launch {
            getAppDataUseCaseImpl().collectLatest { appConfiguration ->
                themeMode.value = appConfiguration.themeStyle
                languageType.value = appConfiguration.languageType
            }
        }
    }

    fun changeThemeStyle(appThemeType: AppThemeType) {
        viewModelScope.launch {
            changeThemeStyleUseCase(appThemeType = appThemeType)
        }
    }

    fun changeLocale(languageType: LanguageType) {
        viewModelScope.launch {
            changeLocaleUseCase(languageType = languageType)
            this@SettingViewModel.languageType.value = languageType
        }
    }
}