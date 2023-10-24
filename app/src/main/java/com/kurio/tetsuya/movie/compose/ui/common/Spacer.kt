package com.kurio.tetsuya.movie.compose.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.kurio.tetsuya.movie.compose.R

@Composable
fun Spacer(spacing: Int = R.dimen.spacing_large) {
    Spacer(modifier = Modifier.height(dimensionResource(id = spacing)))
}

@Composable
fun Spacer1x(spacing: Int = R.dimen.spacing_large) {
    Spacer(modifier = Modifier.height(dimensionResource(spacing)))
}

@Composable
fun HorizontalSpacer(spacing: Int = R.dimen.spacing_normal) {
    Spacer(modifier = Modifier.width(dimensionResource(id = spacing)))
}

@Composable
fun HorizontalSpacer1x(spacing: Int = R.dimen.spacing_large) {
    Spacer(modifier = Modifier.width(dimensionResource(id = spacing)))
}