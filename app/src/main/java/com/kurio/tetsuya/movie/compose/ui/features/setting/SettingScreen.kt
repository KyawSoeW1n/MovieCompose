package com.kurio.tetsuya.movie.compose.ui.features.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kurio.tetsuya.movie.compose.core.theme.AppThemeType
import com.kurio.tetsuya.movie.compose.ui.common.CommonAppBar
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun SettingScreen(
    settingViewModel: SettingViewModel = hiltViewModel()
) {
    val appTheme = settingViewModel.themeMode.collectAsStateWithLifecycle()
    Column {
        CommonAppBar(title = "Setting")
        Column {
            ThemeRadioButton(
                label = "☀️",
                selected = appTheme.value == AppThemeType.LIGHT,
                onClick = {
                    settingViewModel.changeThemeStyle(appThemeType = AppThemeType.LIGHT)
                }
            )
            ThemeRadioButton(
                label = "🌘",
                selected = appTheme.value == AppThemeType.DARK,
                onClick = {
                    settingViewModel.changeThemeStyle(appThemeType = AppThemeType.DARK)
                }
            )
            ThemeRadioButton(
                label = "🤖",
                selected = appTheme.value == AppThemeType.SYSTEM,
                onClick = {
                    settingViewModel.changeThemeStyle(appThemeType = AppThemeType.SYSTEM)
                }
            )
        }
    }
}