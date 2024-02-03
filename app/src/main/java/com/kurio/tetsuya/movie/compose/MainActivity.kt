package com.kurio.tetsuya.movie.compose

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.kurio.tetsuya.movie.compose.core.color.colors
import com.kurio.tetsuya.movie.compose.core.navigation.AppNavigation
import com.kurio.tetsuya.movie.compose.core.style.Typography
import com.kurio.tetsuya.movie.compose.core.theme.DarkColorScheme
import com.kurio.tetsuya.movie.compose.core.theme.LightColorScheme
import com.kuriotetsuya.data.model.toLightColor
import com.kuriotetsuya.domain.AppThemeType
import com.ramcosta.composedestinations.annotation.RootNavGraph
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@RootNavGraph(start = true)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainViewModel: MainViewModel = hiltViewModel()
            val vmState by mainViewModel.mainVM.collectAsStateWithLifecycle()
            LaunchedEffect(key1 = true) {
                mainViewModel.watchAppConfigurationStream()
            }
            MovieAppTheme(
                themeType = vmState.themeMode,
                dynamicColor = vmState.isDynamicColor,
                dynamicColorName = vmState.dynamicColorName,
            ) {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}

@Composable
fun MovieAppTheme(
    themeType: AppThemeType,
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    dynamicColorName: String = "",
    content: @Composable () -> Unit
) {
    val isDynamicColor = dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    val colorScheme = when (themeType) {
        AppThemeType.LIGHT -> LightColorScheme
        AppThemeType.DARK -> DarkColorScheme
        AppThemeType.SYSTEM -> if (darkTheme) DarkColorScheme else LightColorScheme
        AppThemeType.DYNAMIC -> {
            if (isDynamicColor) {
                if (dynamicColorName.isNotEmpty()) {
                    val color = colors.find { it.name == dynamicColorName }
                    color?.toLightColor() ?: LightColorScheme
                } else {
                    if (darkTheme) DarkColorScheme else LightColorScheme
                }
            } else {
                if (darkTheme) DarkColorScheme else LightColorScheme
            }
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}