package com.kurio.tetsuya.movie.compose.ui.features.movedetail.ui

import android.annotation.SuppressLint
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kurio.tetsuya.movie.compose.core.Dimens
import com.kurio.tetsuya.movie.compose.presentation.com.example.domain.ViewState
import com.kurio.tetsuya.movie.compose.ui.common.ErrorTextView
import com.kurio.tetsuya.movie.compose.ui.common.FullLoadingView
import com.kurio.tetsuya.movie.compose.ui.common.MovieDetailsScroller
import com.kurio.tetsuya.movie.compose.ui.common.ToolbarState
import com.kurio.tetsuya.movie.compose.ui.features.movedetail.viewmodel.MovieDetailViewModel
import com.kuriotetsuya.domain.model.MovieDetailVO
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Destination
@Composable
fun MovieDetailScreen(
    navigator: DestinationsNavigator,
    movieId: Int,
    isUpcoming: Boolean,
    moviePoster: String,
    movieTitle: String,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel(),
) {

    val isFetched = rememberSaveable { true }
    val movieDetail = movieDetailViewModel.movieDetailStateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = isFetched) {
        movieDetailViewModel.changeMovieId(movieId = movieId)
    }

    MovieDetailState(
        navigator = navigator,
        moviePoster = moviePoster,
        movieTitle = movieTitle,
        isUpcoming = isUpcoming,
        movieDetail,
    )

}

@Composable
fun MovieDetailState(
    navigator: DestinationsNavigator,
    moviePoster: String,
    movieTitle: String,
    isUpcoming: Boolean,
    movieDetailState: State<ViewState<MovieDetailVO>>,
) {
    val scrollState = rememberScrollState()
    val toolbarHeightPx = with(LocalDensity.current) {
        Dimens.MovieDetailAppBarHeight.roundToPx().toFloat()
    }
    val toolbarOffsetHeightPx = remember { mutableFloatStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.floatValue + delta
                toolbarOffsetHeightPx.floatValue =
                    newOffset.coerceIn(-toolbarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }
    var plantScroller by remember {
        mutableStateOf(MovieDetailsScroller(scrollState, Float.MIN_VALUE))
    }

    val transitionState =
        remember(plantScroller) { plantScroller.toolbarTransitionState }
    val transition = updateTransition(transitionState, label = "")
    val toolbarState = plantScroller.getToolbarState(LocalDensity.current)
    val toolbarAlpha = transition.animateFloat(
        transitionSpec = { spring(stiffness = Spring.StiffnessLow) }, label = ""
    ) { toolbarTransitionState ->
        if (toolbarTransitionState == ToolbarState.HIDDEN) 0f else 1f
    }
    val contentAlpha = transition.animateFloat(
        transitionSpec = { spring(stiffness = Spring.StiffnessLow) }, label = ""
    ) { toolbarTransitionState ->
        if (toolbarTransitionState == ToolbarState.HIDDEN) 1f else 0f
    }
    when (movieDetailState.value) {
        is ViewState.Loading -> {
            FullLoadingView()
        }

        is ViewState.Success -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(nestedScrollConnection)
                    .background(color = MaterialTheme.colorScheme.onBackground)
            ) {
                MovieDetailsContent(
                    movieDetailVO = (movieDetailState.value as ViewState.Success<MovieDetailVO>).successData,
                    moviePoster = moviePoster,
                    scrollState = scrollState,
                    toolbarState = toolbarState,
                    onNamePosition = { newNamePosition ->
                        if (plantScroller.namePosition == Float.MIN_VALUE) {
                            plantScroller =
                                plantScroller.copy(namePosition = newNamePosition)
                        }
                    },
                    imageHeight = with(LocalDensity.current) {
                        val candidateHeight =
                            Dimens.MovieDetailAppBarHeight + toolbarOffsetHeightPx.floatValue.toDp()
                        maxOf(candidateHeight, 1.dp)
                    },
                    contentAlpha = { contentAlpha.value },
                    navigator = navigator,
                    isUpcoming = isUpcoming
                )
                MovieToolbar(
                    toolbarState, movieTitle,
                    toolbarAlpha = { toolbarAlpha.value },
                    contentAlpha = { contentAlpha.value },
                    onBackClick = {
                        navigator.navigateUp()
                    }
                )
            }
        }

        else -> {
            ErrorTextView()
        }
    }
}