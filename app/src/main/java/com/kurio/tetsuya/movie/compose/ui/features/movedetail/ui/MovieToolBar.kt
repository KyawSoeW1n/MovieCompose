package com.kurio.tetsuya.movie.compose.ui.features.movedetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.kurio.tetsuya.movie.compose.core.Dimens
import com.kurio.tetsuya.movie.compose.ui.common.PrimaryTextView
import com.kurio.tetsuya.movie.compose.ui.common.ToolbarState
import com.kurio.tetsuya.movie.compose.ui.common.isShown

@Composable
fun MovieToolbar(
    toolbarState: ToolbarState,
    plantName: String,
    onBackClick: () -> Unit,
    toolbarAlpha: () -> Float,
    contentAlpha: () -> Float
) {
    if (toolbarState.isShown) {
        MovieDetailsToolbar(
            movieName = plantName,
            onBackClick = onBackClick,
            modifier = Modifier.alpha(toolbarAlpha())
        )
    } else {
        MovieHeaderActions(
            modifier = Modifier.alpha(contentAlpha()),
            onBackClick = onBackClick,
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieDetailsToolbar(
    movieName: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface {
        TopAppBar(
            modifier = modifier
                .statusBarsPadding()
                .background(color = MaterialTheme.colorScheme.surface),
            title = {
                Row {
                    IconButton(
                        onBackClick,
                        Modifier.align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                    PrimaryTextView(
                        text = movieName,
                        textStyle = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .wrapContentSize(Alignment.CenterStart),
                        maxLines = 1
                    )
                }
            }
        )
    }
}

@Composable
private fun MovieHeaderActions(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(top = Dimens.ToolbarIconPadding),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val iconModifier = Modifier
            .sizeIn(
                maxWidth = Dimens.ToolbarIconSize,
                maxHeight = Dimens.ToolbarIconSize
            )
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = CircleShape
            )

        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .padding(
                    start = Dimens.PaddingLarge,
                    top = Dimens.PaddingNormal
                )
                .then(iconModifier)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
            )
        }
    }
}
