package com.kurio.tetsuya.movie.compose

import androidx.lifecycle.viewModelScope
import com.kurio.tetsuya.movie.compose.presentation.BaseViewModel
import com.kuriotetsuya.domain.AppThemeType
import com.kuriotetsuya.domain.theme.AppConfigurationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appConfigurationUseCase: AppConfigurationUseCase,
) : BaseViewModel() {
    private val _themeMode = MutableStateFlow(value = AppThemeType.LIGHT)
    val themeMode = _themeMode.asStateFlow()
    private val _isDynamicColor = MutableStateFlow(value = false)
    val isDynamicColor = _isDynamicColor.asStateFlow()
    private val _dynamicColorName = MutableStateFlow(value = "")
    val dynamicColorName = _dynamicColorName.asStateFlow()

    init {
        watchAppConfigurationStream()
    }

    private fun watchAppConfigurationStream() {
        viewModelScope.launch {
            appConfigurationUseCase.getAppConfiguration().collectLatest {
                _dynamicColorName.value = it.dynamicColorCode
                _isDynamicColor.value = it.useDynamicColors
                _themeMode.value = it.themeStyle
            }
        }
    }
}