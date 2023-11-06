package com.kurio.tetsuya.movie.compose.ui.features

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kurio.tetsuya.movie.compose.ui.common.CommonAppBar
import com.kurio.tetsuya.movie.compose.ui.common.Tabs
import com.kurio.tetsuya.movie.compose.ui.common.TabsContent
import com.kurio.tetsuya.movie.compose.ui.features.destinations.SettingScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalFoundationApi::class)
@Composable
@RootNavGraph(start = true)
@Destination
fun MainScreen(navigator: DestinationsNavigator) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        2
    }

    Column(
        modifier = Modifier.background(Color.White)
    ) {
        CommonAppBar(title = "Movie") {
            IconButton(onClick = {
                navigator.navigate(SettingScreenDestination)
            }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surfaceTint
                )
            }
        }
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState, navigator)
    }
}