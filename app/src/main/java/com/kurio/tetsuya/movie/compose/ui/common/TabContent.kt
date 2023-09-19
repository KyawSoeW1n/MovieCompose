package com.kurio.tetsuya.movie.compose.ui.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import com.kurio.tetsuya.movie.compose.ui.features.popular.PopularScreen
import com.kurio.tetsuya.movie.compose.ui.features.upcoming.UpcomingScreen
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(pagerState: PagerState, navigator: DestinationsNavigator) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> UpcomingScreen(navigator = navigator)
            1 -> PopularScreen(navigator = navigator)
        }
    }
}