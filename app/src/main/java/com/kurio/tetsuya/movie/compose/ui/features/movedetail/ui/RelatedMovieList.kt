package com.kurio.tetsuya.movie.compose.ui.features.movedetail.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.RelatedMovieVO
import com.kurio.tetsuya.movie.compose.presentation.ViewState
import com.kurio.tetsuya.movie.compose.ui.common.PartialLoadingView
import com.kurio.tetsuya.movie.compose.ui.common.PrimaryTextView
import com.kurio.tetsuya.movie.compose.ui.features.destinations.MovieDetailScreenDestination
import com.kurio.tetsuya.movie.compose.ui.features.movedetail.viewmodel.MovieDetailViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun RelatedMovieList(
    modifier: Modifier = Modifier,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val relatedMovie = movieDetailViewModel.relatedMovieStateFlow.collectAsStateWithLifecycle()

    when (relatedMovie.value) {
        is ViewState.Loading -> {
            PartialLoadingView()
        }

        is ViewState.Success -> {
            LazyRow(modifier = modifier.padding(horizontal = 16.dp)) {
                items((relatedMovie.value as ViewState.Success<List<RelatedMovieVO>>).successData) { item ->
                    RelateMovie(
                        item = item,
                        clickItem = {
                            navigator.navigate(
                                MovieDetailScreenDestination(
                                    movieId = item.id,
                                    moviePoster = item.image,
                                    movieTitle = item.image
                                )
                            )
                        }
                    )
                }
            }

        }

        is ViewState.Error -> {
            PrimaryTextView(text = "Error")
        }

        else -> {
            PrimaryTextView(text = "Error")
        }
    }


}