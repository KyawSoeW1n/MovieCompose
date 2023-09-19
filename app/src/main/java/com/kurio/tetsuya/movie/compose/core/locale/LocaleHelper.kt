package com.kurio.tetsuya.movie.compose.core.locale

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import com.kurio.tetsuya.movie.compose.MovieApp
import com.kurio.tetsuya.movie.compose.extensions.showLog
import java.util.Locale

object LocaleHelper {
    fun setLocale(context: Context, language: String): Context? {
        MovieApp.myLang = language
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language);
        }
        return updateResourcesLegacy(context, language);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        showLog(">>>> $language")
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }

    private fun updateResourcesLegacy(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }
}