package com.kurio.tetsuya.movie.compose.core.locale

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.kurio.tetsuya.movie.compose.MainActivity
import java.util.Locale

object LocaleHelper {
    fun changeLocale(context: Context, locale: String, onClick: () -> Unit) {
        val appLocale = LocaleList(Locale(locale))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val localeManager =
                (context as? MainActivity)!!.getSystemService(LocaleManager::class.java)
            localeManager.applicationLocales = appLocale
        } else {
            val locales = LocaleListCompat.forLanguageTags(locale)
            AppCompatDelegate.setApplicationLocales(locales)
            (context as? MainActivity)!!.recreate()
        }
        onClick()
    }
}