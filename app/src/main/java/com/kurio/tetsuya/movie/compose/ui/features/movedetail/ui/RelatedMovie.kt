package com.kurio.tetsuya.movie.compose.ui.features.movedetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.kurio.tetsuya.movie.compose.ui.common.AppImageView
import com.kurio.tetsuya.movie.compose.ui.common.PrimaryTextView
import com.kuriotetsuya.domain.model.RelatedMovieVO

@Composable
fun RelateMovie(
    item: RelatedMovieVO,
    clickItem: (item: RelatedMovieVO) -> Unit,
) {
    val gradientColors = listOf(Color.Transparent, Color.Black)
    ConstraintLayout(
        modifier = Modifier
            .clickable {
                clickItem(item)
            }
            .padding(4.dp)
    ) {
        val (image, title) = createRefs()
        AppImageView(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .width(100.dp)
                .height(150.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            imageUrl = item.image
        )
        Box(
            modifier = Modifier
                .background(brush = Brush.verticalGradient(gradientColors))
                .width(0.dp)
                .padding(4.dp)
                .constrainAs(title) {
                    bottom.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }) {
            PrimaryTextView(
                text = item.name,
                textColor = Color.White, maxLines = 1
            )

        }

    }
}

@Preview
@Composable
fun PreviewRelateMovie() {
    RelateMovie(
        RelatedMovieVO(
            id = 0,
            name = "Testing",
            rating = "8.5",
            image = "Testing",
        ),
        clickItem = {

        }
    )
}