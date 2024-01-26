package com.kurio.tetsuya.movie.compose.ui.features.movedetail.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kurio.tetsuya.movie.compose.presentation.com.example.domain.ViewState
import com.kurio.tetsuya.movie.compose.ui.common.PartialLoadingView
import com.kurio.tetsuya.movie.compose.ui.common.PrimaryTextView
import com.kurio.tetsuya.movie.compose.ui.features.destinations.MovieDetailScreenDestination
import com.kurio.tetsuya.movie.compose.ui.features.movedetail.viewmodel.MovieDetailViewModel
import com.kuriotetsuya.domain.model.MovieItemVO
import com.kuriotetsuya.domain.model.RelatedMovieVO
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun RelatedMovieList(
    modifier: Modifier = Modifier,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val relatedMovie = listOf(
        RelatedMovieVO(id = 1, name = "testing", image = "erwr", rating = "0.3")
    )

    LazyRow(modifier = modifier.padding(horizontal = 16.dp)) {
        items(
            relatedMovie,
            key = { item -> item.id }
        ) { item ->
            RelateMovie(
                item = item,
                clickItem = {
//                    navigator.navigate(
//                        MovieDetailScreenDestination(
//                            movieId = item.id,
//                            moviePoster = item.image,
//                            movieTitle = item.image,
//                            isUpcoming = false,
//                        )
//                    )
                }
            )
        }
//    val relatedMovie = movieDetailViewModel.relatedMovieStateFlow.collectAsStateWithLifecycle()
//    when (relatedMovie.value) {
//        is ViewState.Loading -> {
//            PartialLoadingView()
//        }
//
//        is ViewState.Success -> {
//            LazyRow(modifier = modifier.padding(horizontal = 16.dp)) {
//                items(
//                    (relatedMovie.value as ViewState.Success<List<RelatedMovieVO>>).successData,
//                    key = { item -> item.id }
//                ) { item ->
//                    RelateMovie(
//                        item = item,
//                        clickItem = {
//                            navigator.navigate(
//                                MovieDetailScreenDestination(
//                                    movieId = item.id,
//                                    moviePoster = item.image,
//                                    movieTitle = item.image,
//                                    isUpcoming = false,
//                                )
//                            )
//                        }
//                    )
//                }
//            }
//
//        }
//
//        is ViewState.Error -> {
//            PrimaryTextView(text = "Error")
//        }
//
//        else -> {
//            PrimaryTextView(text = "Error")
//        }
//    }
    }
}