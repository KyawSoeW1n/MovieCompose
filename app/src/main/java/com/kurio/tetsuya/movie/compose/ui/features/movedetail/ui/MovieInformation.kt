package com.kurio.tetsuya.movie.compose.ui.features.movedetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kurio.tetsuya.movie.compose.core.Dimens
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.MovieDetailVO
import com.kurio.tetsuya.movie.compose.extensions.visible
import com.kurio.tetsuya.movie.compose.ui.common.PrimaryTextView
import com.kurio.tetsuya.movie.compose.ui.common.ToolbarState

@Composable
fun MovieInformation(
    movieDetailVO: MovieDetailVO,
    onNamePosition: (Float) -> Unit,
    toolbarState: ToolbarState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(
            start = Dimens.PaddingNormal,
            end = Dimens.PaddingNormal,
            top = Dimens.PaddingLarge,
            bottom = Dimens.PaddingNormal
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    start = Dimens.PaddingSmall,
                    end = Dimens.PaddingSmall,
                )
                .onGloballyPositioned { onNamePosition(it.positionInWindow().y) }
                .visible { toolbarState == ToolbarState.HIDDEN }
        ) {
            PrimaryTextView(
                text = movieDetailVO.genres,
                textStyle = MaterialTheme.typography.labelMedium,
                modifier = Modifier.weight(1f)
            )

            Box(
                modifier = Modifier
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                PrimaryTextView(
                    text = movieDetailVO.rating,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    textStyle = MaterialTheme.typography.labelSmall,
                    textColor = Color.White
                )
            }

        }
        PrimaryTextView(
            text = "${movieDetailVO.overview}",
            modifier = Modifier
                .padding(
                    start = Dimens.PaddingSmall,
                    top = Dimens.PaddingNormal,
                    end = Dimens.PaddingSmall,
                )
                .align(Alignment.CenterHorizontally)
                .onGloballyPositioned { onNamePosition(it.positionInWindow().y) }
                .visible { toolbarState == ToolbarState.HIDDEN },
            textStyle = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )
    }
}