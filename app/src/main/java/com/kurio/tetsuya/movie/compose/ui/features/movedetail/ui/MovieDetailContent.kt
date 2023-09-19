package com.kurio.tetsuya.movie.compose.ui.features.movedetail.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.MovieDetailVO
import com.kurio.tetsuya.movie.compose.ui.common.AppImageView
import com.kurio.tetsuya.movie.compose.ui.common.PrimaryTextView
import com.kurio.tetsuya.movie.compose.ui.common.ToolbarState
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
    navigator: DestinationsNavigator
) {
    Column(Modifier.verticalScroll(scrollState)) {
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
            PrimaryTextView(
                text = movieDetailVO.name,
                textColor = Color.White,
                textStyle = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 16.dp)
                    .constrainAs(name) {
                        start.linkTo(image.start)
                        bottom.linkTo(image.bottom)
                    })
            PlantInformation(
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
