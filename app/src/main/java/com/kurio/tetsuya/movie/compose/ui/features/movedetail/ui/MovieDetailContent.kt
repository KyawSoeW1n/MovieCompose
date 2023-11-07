package com.kurio.tetsuya.movie.compose.ui.features.movedetail.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.MovieDetailVO
import com.kurio.tetsuya.movie.compose.ui.common.AppImageView
import com.kurio.tetsuya.movie.compose.ui.common.PrimaryTextView
import com.kurio.tetsuya.movie.compose.ui.common.ToolbarState
import com.kurio.tetsuya.movie.compose.ui.features.movedetail.viewmodel.MovieDetailViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun MovieDetailsContent(
    movieDetailVO: MovieDetailVO,
    moviePoster: String,
    scrollState: ScrollState,
    toolbarState: ToolbarState,
    imageHeight: Dp,
    onNamePosition: (Float) -> Unit,
    contentAlpha: () -> Float,
    navigator: DestinationsNavigator,
    isUpcoming: Boolean,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel(),
) {
    val isFavourite =
        movieDetailViewModel.getMovieDetailStatus(movieDetailVO.id, isUpcoming)
            .collectAsStateWithLifecycle(
                initialValue = false
            ).value
    Column(
        Modifier
            .verticalScroll(scrollState)
            .background(color = MaterialTheme.colorScheme.onBackground)
    ) {
        ConstraintLayout {
            val (image, name, info, relatedMovie) = createRefs()
            AppImageView(
                imageUrl = moviePoster,
                modifier = Modifier
                    .height(imageHeight)
                    .constrainAs(image) { top.linkTo(parent.top) }
                    .alpha(contentAlpha()),
                contentScale = ContentScale.FillBounds
            )
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 16.dp)
                    .constrainAs(name) {
                        start.linkTo(image.start)
                        bottom.linkTo(image.bottom)
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PrimaryTextView(
                    text = movieDetailVO.name,
                    textColor = Color.White,
                    textStyle = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = {
                    if (isUpcoming) {
                        movieDetailViewModel.changeFavouriteUpcomingStatus(
                            movieDetailVO.id,
                            !isFavourite
                        )
                    } else {
                        movieDetailViewModel.changeFavouritePopularStatus(
                            movieDetailVO.id,
                            !isFavourite
                        )
                    }
                }) {
                    if (isFavourite) Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        tint = Color.Red
                    ) else
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = null,
                            tint = Color.Red
                        )
                }
            }

            MovieInformation(
                movieDetailVO = movieDetailVO,
                onNamePosition = { onNamePosition(it) },
                toolbarState = toolbarState,
                modifier = Modifier.constrainAs(info) {
                    top.linkTo(image.bottom)
                }
            )
            RelatedMovieList(
                modifier = Modifier.constrainAs(relatedMovie) {
                    top.linkTo(info.bottom)
                },
                navigator = navigator
            )
        }
    }
}
