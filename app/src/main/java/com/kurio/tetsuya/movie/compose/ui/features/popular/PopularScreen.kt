package com.kurio.tetsuya.movie.compose.ui.features.popular

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kurio.tetsuya.movie.compose.ui.features.MovieItem
import com.kurio.tetsuya.movie.compose.ui.features.destinations.MovieDetailScreenDestination
import com.kurio.tetsuya.movie.compose.ui.features.popular.viewmodel.PopularViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun PopularScreen(
    popularViewModel: PopularViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val movieList =
        popularViewModel.getCachePopularList().collectAsState(initial = mutableListOf())
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = rememberLazyGridState(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(
            items = movieList.value.toList(),
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
                        )
                    )
                }
            )
        }
    }
}