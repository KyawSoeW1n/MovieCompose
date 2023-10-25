package com.kurio.tetsuya.movie.compose.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun PrimaryOutlineTextField(
    value: MutableState<TextFieldValue>,
    label: String,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    innerModifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value.value,
            onValueChange = { newValue ->
                value.value = newValue
                onValueChange(newValue)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Gray,
            ),
            label = {
                PrimaryTextView(label)
            },
            modifier = innerModifier,
            textStyle = textStyle,
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
        )
    }
}