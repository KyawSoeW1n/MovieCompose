package com.kurio.tetsuya.movie.compose.ui.features.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kurio.tetsuya.movie.compose.ui.common.CommonPullToRefreshIndicator
import com.kurio.tetsuya.movie.compose.ui.features.MovieItem
import com.kurio.tetsuya.movie.compose.ui.features.destinations.MovieDetailScreenDestination
import com.kurio.tetsuya.movie.compose.ui.features.popular.viewmodel.PopularViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.collections.immutable.persistentListOf

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PopularScreen(
    popularViewModel: PopularViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val movieList =
        popularViewModel.getCachePopularList()
            .collectAsStateWithLifecycle(initialValue = persistentListOf()).value
    val isRefresh = popularViewModel.isRefreshing.collectAsStateWithLifecycle().value

    val pullRefreshState =
        rememberPullRefreshState(isRefresh, { popularViewModel.refresh() })

    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .background(color = MaterialTheme.colorScheme.onBackground)
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = movieList,
                key = { item -> item.id },
            ) { item ->
                MovieItem(
                    item = item,
                    changeFavouriteStatus = {
                        popularViewModel.changeFavouriteStatus(it.id, !it.isFavourite)
                    },
                    clickItem = {
                        navigator.navigate(
                            MovieDetailScreenDestination(
                                movieId = it.id,
                                moviePoster = it.image,
                                movieTitle = it.title,
                                isUpcoming = false,
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