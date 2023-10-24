package com.kurio.tetsuya.movie.compose.core.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.kurio.tetsuya.movie.compose.ui.features.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.rememberNavHostEngine


@Composable
fun AppNavigation(
    navController: NavHostController,
) {
    val navHostEngine = rememberNavHostEngine(
        rootDefaultAnimations = RootNavGraphDefaultAnimations(
            enterTransition = { fadeIn(animationSpec = tween(400)) },
            exitTransition = { fadeOut(animationSpec = tween(400)) }
        )
    )
    DestinationsNavHost(
        navGraph = NavGraphs.root,
        engine = navHostEngine,
        navController = navController
    )
}