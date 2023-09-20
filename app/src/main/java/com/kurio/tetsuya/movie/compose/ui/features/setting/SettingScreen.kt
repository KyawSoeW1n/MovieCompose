package com.kurio.tetsuya.movie.compose.ui.features.setting

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kurio.tetsuya.movie.compose.MainActivity
import com.kurio.tetsuya.movie.compose.R
import com.kurio.tetsuya.movie.compose.core.locale.LanguageType
import com.kurio.tetsuya.movie.compose.core.theme.AppThemeType
import com.kurio.tetsuya.movie.compose.ui.common.CommonAppBar
import com.kurio.tetsuya.movie.compose.ui.common.PrimaryTextView
import com.ramcosta.composedestinations.annotation.Destination
import java.util.Locale

@Composable
@Destination
fun SettingScreen(
    settingViewModel: SettingViewModel = hiltViewModel()
) {
    val appTheme = settingViewModel.themeMode.collectAsStateWithLifecycle()
    val languageType = settingViewModel.languageTypettt.collectAsStateWithLifecycle()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.onBackground)
            .fillMaxSize()
    ) {
        CommonAppBar(title = "Setting")
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
                selected = appTheme.value == AppThemeType.LIGHT,
                onClick = {
                    settingViewModel.changeThemeStyle(appThemeType = AppThemeType.LIGHT)
                }
            )
            ThemeRadioButton(
                label = "🌘\t ${stringResource(id = R.string.dark)}",
                selected = appTheme.value == AppThemeType.DARK,
                onClick = {
                    settingViewModel.changeThemeStyle(appThemeType = AppThemeType.DARK)
                }
            )
            ThemeRadioButton(
                label = "🤖\t ${stringResource(id = R.string.system)}",
                selected = appTheme.value == AppThemeType.SYSTEM,
                onClick = {
                    settingViewModel.changeThemeStyle(appThemeType = AppThemeType.SYSTEM)
                }
            )

            PrimaryTextView(
                text = "Language",
                modifier = Modifier.padding(start = 16.dp),
                fontWeight = FontWeight.Medium,
                textStyle = MaterialTheme.typography.titleMedium
            )
            ThemeRadioButton(
                label = "English",
                selected = languageType.value == LanguageType.ENGLISH,
                onClick = {
                    changeLocale(context = context, "en", onClick = {
                        settingViewModel.changeLanguageType(languageType = LanguageType.ENGLISH)
                    })
                }
            )

            ThemeRadioButton(
                label = "Burmese",
                selected = languageType.value == LanguageType.MYANMAR,
                onClick = {
                    changeLocale(context = context,
                        "my",
                        onClick = {
                            settingViewModel.changeLanguageType(languageType = LanguageType.MYANMAR)
                        })
                }
            )
        }
    }
}

private fun changeLocale(context: Context, locale: String, onClick: () -> Unit) {
    val appLocale = LocaleList(Locale(locale))


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val localeManager = (context as? MainActivity)!!.getSystemService(LocaleManager::class.java)
        localeManager.applicationLocales = appLocale
//        (context as? MainActivity)!!.recreate()
    }
    onClick()
}

@Preview(name = "SettingPreview")
@Composable
fun PreviewSetting() {
    SettingScreen()
}