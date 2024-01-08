package com.kurio.tetsuya.movie.compose.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.kurio.tetsuya.movie.compose.R

@Composable
@NonRestartableComposable
fun SpacerX(spacing: Int = R.dimen.spacing_normal) {
    Spacer(modifier = Modifier.height(dimensionResource(id = spacing)))
}

@Composable
@NonRestartableComposable
fun Spacer1x(spacing: Int = R.dimen.spacing_large) {
    Spacer(modifier = Modifier.height(dimensionResource(spacing)))
}

@Composable
@NonRestartableComposable
fun HorizontalSpacer(spacing: Int = R.dimen.spacing_normal) {
    Spacer(modifier = Modifier.width(dimensionResource(id = spacing)))
}

@Composable
@NonRestartableComposable
fun HorizontalSpacer1x(spacing: Int = R.dimen.spacing_large) {
    Spacer(modifier = Modifier.width(dimensionResource(id = spacing)))
}