package com.kurio.tetsuya.movie.compose.ui.features.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.kurio.tetsuya.movie.compose.ui.common.PrimaryTextView


@Composable
fun ThemeRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        PrimaryTextView(label)
    }
}
