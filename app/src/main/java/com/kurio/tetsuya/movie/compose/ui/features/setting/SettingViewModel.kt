package com.kurio.tetsuya.movie.compose.ui.features.setting

import androidx.lifecycle.viewModelScope
import com.kurio.tetsuya.movie.compose.core.locale.LanguageType
import com.kurio.tetsuya.movie.compose.core.theme.AppThemeType
import com.kurio.tetsuya.movie.compose.domain.cache.theme.ChangeThemeStyleUseCaseRepo
import com.kurio.tetsuya.movie.compose.domain.cache.theme.ThemeUseCaseImpl
import com.kurio.tetsuya.movie.compose.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val changeThemeStyleUseCaseRepo: ChangeThemeStyleUseCaseRepo,
    private val themeUseCaseRepo: ThemeUseCaseImpl
) : BaseViewModel() {
    val themeMode = MutableStateFlow(value = AppThemeType.LIGHT)
    val languageTypettt = MutableStateFlow(value = LanguageType.ENGLISH)

    init {
        watchAppConfigurationStream()
    }

    private fun watchAppConfigurationStream() {
        viewModelScope.launch {
            themeUseCaseRepo().collectLatest { appConfiguration ->
                themeMode.value = appConfiguration.themeStyle
            }
        }
    }

    fun changeThemeStyle(appThemeType: AppThemeType) {
        viewModelScope.launch {
            changeThemeStyleUseCaseRepo(appThemeType = appThemeType)
        }
    }

    fun changeLanguageType(languageType: LanguageType) {
        viewModelScope.launch {
            languageTypettt.value = languageType
//            changeThemeStyleUseCaseRepo(appThemeType = languageType)
        }
    }
}