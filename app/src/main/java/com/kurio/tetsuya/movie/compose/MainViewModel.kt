package com.kurio.tetsuya.movie.compose

import androidx.lifecycle.viewModelScope
import com.kurio.tetsuya.movie.compose.core.theme.AppThemeType
import com.kurio.tetsuya.movie.compose.domain.cache.theme.ThemeUseCaseImpl
import com.kurio.tetsuya.movie.compose.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val themeUseCaseImpl: ThemeUseCaseImpl
) : BaseViewModel() {
    val themeMode = MutableStateFlow(value = AppThemeType.LIGHT)
    init {
        watchAppConfigurationStream()
    }

    private fun watchAppConfigurationStream() {
        viewModelScope.launch {
            themeUseCaseImpl().collectLatest {
                themeMode.value = it.themeStyle
            }
        }
    }
}