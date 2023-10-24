package com.kurio.tetsuya.movie.compose.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kurio.tetsuya.movie.compose.core.color.Purple40

@Composable
fun PartialLoadingView() {
    Box(
        modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
