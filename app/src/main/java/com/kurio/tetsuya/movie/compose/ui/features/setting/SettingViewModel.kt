package com.kurio.tetsuya.movie.compose.ui.features.setting

import androidx.lifecycle.viewModelScope
import com.kuriotetsuya.data.LanguageType
import com.kurio.tetsuya.movie.compose.presentation.BaseViewModel
import com.kurio.tetsuya.movie.compose.util.CoroutinesDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
//    private val changeThemeStyleUseCase: ChangeThemeStyleUseCase,
//    private val changeDynamicColorUseCase: ChangeDynamicColorUseCase,
//    private val changeLocaleUseCase: ChangeLocaleUseCase,
//    private val getThemeUseCase: GetThemeUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers,
) : BaseViewModel() {
    val themeMode = MutableStateFlow(value = com.kuriotetsuya.data.AppThemeType.LIGHT)
    val languageType = MutableStateFlow(value = LanguageType.en)
    val isDynamicColor = MutableStateFlow(value = false)
//
//    init {
//        watchAppConfigurationStream()
//    }
//
//    private fun watchAppConfigurationStream() {
//        viewModelScope.launch(coroutinesDispatchers.io) {
//            getThemeUseCase.getThemeMode().collectLatest { appConfiguration ->
//                isDynamicColor.value = appConfiguration.useDynamicColors
//                themeMode.value = appConfiguration.themeStyle
//                languageType.value = appConfiguration.languageType
//            }
//        }
//    }
//
//    fun changeThemeStyle(appThemeType: com.kuriotetsuya.data.AppThemeType) {
//        viewModelScope.launch(coroutinesDispatchers.io) {
//            changeThemeStyleUseCase(appThemeType = appThemeType)
//        }
//    }
//
//    fun toggleDynamicColor() {
//        viewModelScope.launch(coroutinesDispatchers.io) {
//            changeDynamicColorUseCase.toggle()
//        }
//    }
//
//    fun setDynamicColorCode(dynamicColorName: String) {
//        viewModelScope.launch(coroutinesDispatchers.io) {
//            changeDynamicColorUseCase.setDynamicColorCode(dynamicColorName = dynamicColorName)
//        }
//    }
//
//    fun changeLocale(languageType: LanguageType) {
//        viewModelScope.launch(coroutinesDispatchers.io) {
//            changeLocaleUseCase(languageType = languageType)
//            this@SettingViewModel.languageType.value = languageType
//        }
//    }
}