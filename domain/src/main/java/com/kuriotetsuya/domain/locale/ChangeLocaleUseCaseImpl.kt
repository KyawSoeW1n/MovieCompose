//package locale
//
//import com.kurio.tetsuya.movie.compose.core.locale.LanguageType
//import com.kurio.tetsuya.movie.compose.data.cache.app.AppPreferencesDataStore
//import javax.inject.Inject
//
//
//class ChangeLocaleUseCaseImpl @Inject constructor(
//    private val appPreferencesDataStore: AppPreferencesDataStore
//) : ChangeLocaleUseCase {
//
//    override suspend fun invoke(languageType: LanguageType) =
//        appPreferencesDataStore.changeLocale(languageType = languageType)
//}