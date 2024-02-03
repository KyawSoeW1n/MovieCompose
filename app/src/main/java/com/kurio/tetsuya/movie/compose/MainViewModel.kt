package com.kurio.tetsuya.movie.compose

import androidx.lifecycle.viewModelScope
import com.kurio.tetsuya.movie.compose.presentation.BaseViewModel
import com.kuriotetsuya.domain.AppThemeType
import com.kuriotetsuya.domain.theme.AppConfigurationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appConfigurationUseCase: AppConfigurationUseCase,
) : BaseViewModel() {

    private val _mainVM = MutableStateFlow(
        value = MainVM(
            themeMode = AppThemeType.LIGHT,
            isDynamicColor = false,
            dynamicColorName = ""
        )
    )
    val mainVM = _mainVM.asStateFlow()

    fun watchAppConfigurationStream() {
        viewModelScope.launch {
            appConfigurationUseCase.getAppConfiguration().collectLatest {
                _mainVM.update { vm ->
                    vm.copy(
                        themeMode = it.themeStyle,
                        dynamicColorName = it.dynamicColorCode,
                        isDynamicColor = it.useDynamicColors,
                    )
                }
            }
        }
    }
}

data class MainVM(
    val themeMode: AppThemeType,
    val isDynamicColor: Boolean,
    val dynamicColorName: String,
)