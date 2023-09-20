package com.kurio.tetsuya.movie.compose

import android.app.Application
import android.content.Context
import com.kurio.tetsuya.movie.compose.extensions.showLog
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}