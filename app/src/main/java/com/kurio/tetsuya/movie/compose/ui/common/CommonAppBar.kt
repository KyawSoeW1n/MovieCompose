package com.kurio.tetsuya.movie.compose.ui.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonAppBar(title: String, actions: @Composable RowScope.() -> Unit = {}) {
    TopAppBar(
        title = {
            PrimaryTextView(text = title, fontSize = 18.sp)
        },
        actions = actions
    )
}

