package com.kurio.tetsuya.movie.compose.ui.features.upcoming

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kurio.tetsuya.movie.compose.ui.common.CommonPullToRefreshIndicator
import com.kurio.tetsuya.movie.compose.ui.common.PrimaryOutlineTextField
import com.kurio.tetsuya.movie.compose.ui.common.SpacerX
import com.kurio.tetsuya.movie.compose.ui.features.MovieItem
import com.kurio.tetsuya.movie.compose.ui.features.destinations.MovieDetailScreenDestination
import com.kurio.tetsuya.movie.compose.ui.features.upcoming.viewmodel.UpcomingEvent
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

    val filterMovieList =
        upcomingViewModel.getCacheUpcomingListByKeyword()
            .collectAsStateWithLifecycle(initialValue = listOf()).value

    val isRefresh = upcomingViewModel.isRefreshing.collectAsStateWithLifecycle().value
    val upcomingScreenEvent =
        upcomingViewModel.upcomingEventState.collectAsStateWithLifecycle().value
    val pullRefreshState =
        rememberPullRefreshState(isRefresh, { upcomingViewModel.refresh() })

    val textFieldValue =
        rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .background(color = MaterialTheme.colorScheme.onBackground)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            PrimaryOutlineTextField(
                innerModifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = textFieldValue,
                label = "Enter Movie Name",
                onValueChange = {
                    textFieldValue.value = it
                    upcomingViewModel.setKeyword(it.text)
                    if (it.text.isNotEmpty()) {
                        upcomingViewModel.changeUpcomingScreenEvent(
                            UpcomingEvent.SearchEvent(
                                keyword = it.text
                            )
                        )
                    } else {
                        upcomingViewModel.changeUpcomingScreenEvent(UpcomingEvent.ResetEvent)
                    }
                },
            )
            SpacerX()
            LazyVerticalGrid(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                columns = GridCells.Fixed(2),
                state = rememberLazyGridState(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = if (upcomingScreenEvent == UpcomingEvent.ResetEvent) movieList.toList() else filterMovieList,
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
                                    isUpcoming = true,
                                )
                            )
                        }
                    )
                }
            }
        }
        CommonPullToRefreshIndicator(
            isRefreshing = isRefresh,
            pullRefreshState = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }

}