package com.kurio.tetsuya.movie.compose

import androidx.lifecycle.viewModelScope
import com.kurio.tetsuya.movie.compose.presentation.BaseViewModel
import com.kurio.tetsuya.movie.compose.util.CoroutinesDispatchers
import com.kuriotetsuya.domain.AppThemeType
import com.kuriotetsuya.domain.theme.AppConfigurationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appConfigurationUseCase: AppConfigurationUseCase,
    private val dispatchers: CoroutinesDispatchers,
) : BaseViewModel() {
    val themeMode = MutableStateFlow(value = AppThemeType.LIGHT)
    val isDynamicColor = MutableStateFlow(value = false)
    val dynamicColorName = MutableStateFlow(value = "")

    init {
        watchAppConfigurationStream()
    }

    private fun watchAppConfigurationStream() {
        viewModelScope.launch(dispatchers.io) {
            appConfigurationUseCase.getAppConfiguration().collectLatest {
                dynamicColorName.value = it.dynamicColorCode
                isDynamicColor.value = it.useDynamicColors
                themeMode.value = it.themeStyle
            }
        }
    }
}