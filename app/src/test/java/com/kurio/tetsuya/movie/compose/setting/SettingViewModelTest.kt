package com.kurio.tetsuya.movie.compose.setting

import com.kurio.tetsuya.movie.compose.TestDispatcherProvider
import com.kurio.tetsuya.movie.compose.ui.features.setting.SettingViewModel
import com.kurio.tetsuya.movie.compose.util.CoroutinesDispatchers
import com.kuriotetsuya.domain.AppThemeType
import com.kuriotetsuya.domain.LanguageType
import com.kuriotetsuya.domain.theme.AppConfigurationRepo
import com.kuriotetsuya.domain.theme.AppConfigurationUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SettingViewModelTest {
    private lateinit var settingViewModel: SettingViewModel
    private lateinit var coroutinesDispatchers: CoroutinesDispatchers
    private lateinit var appConfigurationUseCase: AppConfigurationUseCase
    private lateinit var getAppConfigurationRepo: AppConfigurationRepo

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        coroutinesDispatchers = TestDispatcherProvider()
        appConfigurationUseCase = mockk(relaxed = true)
        getAppConfigurationRepo = mockk(relaxed = true)
        settingViewModel = SettingViewModel(
            appConfigurationUseCase = appConfigurationUseCase,
            coroutinesDispatchers = coroutinesDispatchers,
        )

    }

    @Test
    fun settingViewModel_Initial_settingUIState() {
        val settingUIState = settingViewModel.settingUIState.value
        assertTrue(settingUIState.themeMode == AppThemeType.LIGHT)
        assertTrue(!settingUIState.isDynamicColor)
        assertTrue(settingUIState.dynamicColorName == "")
        assertTrue(settingUIState.languateType == LanguageType.en)
    }

    @Test
    fun settingViewModel_ChangeThemeStyleToDark() = runTest {
        settingViewModel.changeThemeStyle(AppThemeType.DARK)
        coVerify(exactly = 1) { appConfigurationUseCase.changeAppTheme(AppThemeType.DARK) }
    }

    @Test
    fun settingViewModel_ChangeThemeStyleToLight() = runTest {
        settingViewModel.changeThemeStyle(AppThemeType.LIGHT)
        coVerify(exactly = 1) { appConfigurationUseCase.changeAppTheme(AppThemeType.LIGHT) }
    }

    @Test
    fun settingViewModel_ChangeLanguageTypeToEn() = runTest {
        settingViewModel.changeLocale(LanguageType.en)
        coVerify(exactly = 1) { appConfigurationUseCase.changeLanguage(LanguageType.en) }
    }

    @Test
    fun settingViewModel_TurnOnDynamicColor() = runTest {
        settingViewModel.toggleDynamicColor()
        coVerify(exactly = 1) { appConfigurationUseCase.toggleMode() }
    }

    @Test
    fun settingViewModel_TurnOffDynamicColor() = runTest {
        settingViewModel.toggleDynamicColor()
        settingViewModel.toggleDynamicColor()
        coVerify(exactly = 2) { appConfigurationUseCase.toggleMode() }
    }


    @Test
    fun settingViewModel_SetDynamicColorCode() = runTest {
        settingViewModel.setDynamicColorCode("123456")
        coVerify(exactly = 1) { appConfigurationUseCase.setDynamicColorCode("123456") }
    }
}