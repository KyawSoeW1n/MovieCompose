package com.kurio.tetsuya.movie.compose.ui.features.upcoming

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kurio.tetsuya.movie.compose.ui.common.CommonPullToRefreshIndicator
import com.kurio.tetsuya.movie.compose.ui.features.MovieItem
import com.kurio.tetsuya.movie.compose.ui.features.destinations.MovieDetailScreenDestination
import com.kurio.tetsuya.movie.compose.ui.features.upcoming.viewmodel.UpcomingViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UpcomingScreen(
    upcomingViewModel: UpcomingViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    val movieList =
        upcomingViewModel.getCacheUpcomingList()
            .collectAsStateWithLifecycle(initialValue = listOf()).value

    val isRefresh = upcomingViewModel.isRefreshing.collectAsStateWithLifecycle().value
    val pullRefreshState =
        rememberPullRefreshState(isRefresh, { upcomingViewModel.refresh() })
    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .background(color = MaterialTheme.colorScheme.onBackground)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState(),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(
                items = movieList.toList(),
                key = { item -> item.id },
            ) { item ->
                MovieItem(
                    item = item,
                    changeFavouriteStatus = {
                        upcomingViewModel.changeFavouriteStatus(it.id, !it.isFavourite)
                    },
                    clickItem = {
                        navigator.navigate(
                            MovieDetailScreenDestination(
                                movieId = it.id,
                                moviePoster = it.image,
                                movieTitle = it.title,
                            )
                        )
                    }
                )
            }
        }
        CommonPullToRefreshIndicator(
            isRefreshing = isRefresh,
            pullRefreshState = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }

}