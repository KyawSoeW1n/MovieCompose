package com.kurio.tetsuya.movie.compose

import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class TestNavigator : DestinationsNavigator {
    override fun clearBackStack(route: String): Boolean {
        return true
    }

    override fun navigate(
        route: String,
        onlyIfResumed: Boolean,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ) {
        return
    }

    override fun navigate(
        route: String,
        onlyIfResumed: Boolean,
        builder: NavOptionsBuilder.() -> Unit
    ) {
        return
    }

    override fun navigateUp(): Boolean {
        return true
    }

    override fun popBackStack(): Boolean {
        return true
    }

    override fun popBackStack(route: String, inclusive: Boolean, saveState: Boolean): Boolean {
        return true
    }
}