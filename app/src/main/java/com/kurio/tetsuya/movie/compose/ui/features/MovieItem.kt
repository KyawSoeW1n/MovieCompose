package com.kurio.tetsuya.movie.compose.ui.features

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kurio.tetsuya.movie.compose.data.remote.model.movie.MovieItemVO
import com.kurio.tetsuya.movie.compose.ui.common.AppImageView
import com.kurio.tetsuya.movie.compose.ui.common.PrimaryTextView

@Composable
fun MovieItem(
    item: MovieItemVO,
    changeFavouriteStatus: (item: MovieItemVO) -> Unit,
    clickItem: (item: MovieItemVO) -> Unit
) {
    val gradientColors = listOf(Color.Transparent, Color.Black)
    Box(modifier = Modifier
        .padding(8.dp)
        .clickable {
            clickItem(item)
        }) {
        AppImageView(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .aspectRatio(0.75f),
            imageUrl = item.image
        )
        Box(
            modifier = Modifier
                .background(brush = Brush.verticalGradient(gradientColors))
                .align(alignment = Alignment.BottomCenter),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                PrimaryTextView(
                    text = item.title, modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    textColor = Color.White, textAlign = TextAlign.Start, maxLines = 1
                )
                IconButton(onClick = {
                    changeFavouriteStatus(item)
                }) {
                    if (item.isFavourite) Icon(
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

        }

    }
}