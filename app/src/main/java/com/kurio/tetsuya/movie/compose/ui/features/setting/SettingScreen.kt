package com.kurio.tetsuya.movie.compose.ui.features.setting

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kurio.tetsuya.movie.compose.R
import com.kurio.tetsuya.movie.compose.core.color.colors
import com.kurio.tetsuya.movie.compose.core.locale.LocaleHelper
import com.kurio.tetsuya.movie.compose.ui.common.CommonAppBar
import com.kurio.tetsuya.movie.compose.ui.common.PrimaryTextView
import com.kurio.tetsuya.movie.compose.ui.common.SpacerX
import com.kuriotetsuya.domain.AppThemeType
import com.kuriotetsuya.domain.LanguageType
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun SettingScreen(
    settingViewModel: SettingViewModel = hiltViewModel()
) {
    val settingVM = settingViewModel.settingUIState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        settingViewModel.watchAppConfigurationStream()
    }

    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.onBackground)
            .fillMaxSize()
    ) {
        CommonAppBar(title = "Setting")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            Row {
                PrimaryTextView(
                    text = "Dynamic Theme",
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            top = 8.dp
                        )
                        .weight(1f),
                    fontWeight = FontWeight.Medium,
                    textStyle = MaterialTheme.typography.titleMedium
                )
                Switch(
                    modifier = Modifier.padding(end = 8.dp),
                    checked = settingVM.value.isDynamicColor,
                    onCheckedChange = {
                        if (it) {
                            settingViewModel.changeThemeStyle(appThemeType = com.kuriotetsuya.domain.AppThemeType.DYNAMIC)
                        } else {
                            settingViewModel.changeThemeStyle(appThemeType = com.kuriotetsuya.domain.AppThemeType.SYSTEM)
                        }

                        settingViewModel.toggleDynamicColor()
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        uncheckedThumbColor = Color.Gray

                    )
                )

            }
        if (settingVM.value.isDynamicColor) {
            LazyVerticalGrid(
                modifier = Modifier.padding(horizontal = 8.dp),
                columns = GridCells.Fixed(2),
                content = {
                    items(colors) { item ->
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .background(item.primary)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .clickable {
                                    settingViewModel.setDynamicColorCode(item.name)
                                },
                            contentAlignment = Alignment.Center,
                        ) {
                            PrimaryTextView(
                                item.name,
                                modifier = Modifier
                                    .padding(16.dp),
                                textColor = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                },
            )
        } else {
            Column {
                PrimaryTextView(
                    text = "Theme",
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 8.dp
                    ),
                    fontWeight = FontWeight.Medium,
                    textStyle = MaterialTheme.typography.titleMedium
                )
                ThemeRadioButton(
                    label = "☀️\t ${stringResource(id = R.string.light)}",
                    selected = settingVM.value.themeMode == AppThemeType.LIGHT,
                    onClick = {
                        settingViewModel.changeThemeStyle(appThemeType = AppThemeType.LIGHT)
                    }
                )
                ThemeRadioButton(
                    label = "🌘\t ${stringResource(id = R.string.dark)}",
                    selected = settingVM.value.themeMode == AppThemeType.DARK,
                    onClick = {
                        settingViewModel.changeThemeStyle(appThemeType = AppThemeType.DARK)
                    }
                )
                ThemeRadioButton(
                    label = "🤖\t ${stringResource(id = R.string.system)}",
                    selected = settingVM.value.themeMode == AppThemeType.SYSTEM,
                    onClick = {
                        settingViewModel.changeThemeStyle(appThemeType = AppThemeType.SYSTEM)
                    }
                )
            }
        }
        SpacerX()
        PrimaryTextView(
            text = "Language",
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Medium,
            textStyle = MaterialTheme.typography.titleMedium
        )
        ThemeRadioButton(
            label = "English",
            selected = settingVM.value.languateType == LanguageType.en,
            onClick = {
                LocaleHelper.changeLocale(context = context,
                    LanguageType.en.name,
                    onClick = {
                        settingViewModel.changeLocale(languageType = LanguageType.en)
                    })
            }
        )
        ThemeRadioButton(
            label = "Burmese",
            selected = settingVM.value.languateType == LanguageType.my,
            onClick = {
                LocaleHelper.changeLocale(context = context,
                    LanguageType.my.name,
                    onClick = {
                        settingViewModel.changeLocale(languageType = LanguageType.my)
                    })
            }
        )
    }
}


@Preview(name = "SettingPreview")
@Composable
fun PreviewSetting() {
    SettingScreen()
}