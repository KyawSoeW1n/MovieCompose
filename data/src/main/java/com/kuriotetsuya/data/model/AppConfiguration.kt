import com.kuriotetsuya.data.AppThemeType
import com.kuriotetsuya.data.LanguageType

data class AppConfiguration(
    val useDynamicColors: Boolean,
    val themeStyle: AppThemeType,
    val languageType: LanguageType,
    val dynamicColorCode: String,
)