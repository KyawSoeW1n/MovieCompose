package com.kurio.tetsuya.movie.compose.ui.common

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CommonPullToRefreshIndicator(
    isRefreshing: Boolean,
    pullRefreshState: PullRefreshState,
    modifier: Modifier,
) {
    PullRefreshIndicator(
        refreshing = isRefreshing,
        state = pullRefreshState,
        modifier = modifier,
        contentColor = MaterialTheme.colorScheme.primary
    )
}