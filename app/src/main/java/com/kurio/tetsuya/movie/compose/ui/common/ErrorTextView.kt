package com.kurio.tetsuya.movie.compose.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
@Stable
fun ErrorTextView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        PrimaryTextView(text = "Error")
    }
}