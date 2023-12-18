package com.kurio.tetsuya.movie.benchmark

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.benchmark.macro.ExperimentalBaselineProfilesApi
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalBaselineProfilesApi::class)
@RunWith(AndroidJUnit4::class)
class BaselineProfileGenerator {

    @RequiresApi(Build.VERSION_CODES.P)
    @get:Rule
    val baselineRule = BaselineProfileRule()

    @RequiresApi(Build.VERSION_CODES.P)
    @Test
    fun generateBaselineProfile() = baselineRule.collectBaselineProfile(
        packageName = "com.kurio.tetsuya.movie.compose.dev",
    ) {
        pressHome()
        startActivityAndWait()
    }
}