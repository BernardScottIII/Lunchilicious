package com.scottb4.lunchilicious.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun StatelessNewMenuItemInput (
    value:String,
    label: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    prefix: @Composable (() -> Unit) = {},
    suffix: @Composable (() -> Unit) = {},
    placeholder: @Composable (() -> Unit) = {},
    isError: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueUpdate: (String) -> Unit
) {
    Row (
        modifier = modifier.fillMaxWidth()
    ) {
        TextField(
            modifier = modifier
                .padding(24.dp),
            value = value,
            onValueChange = onValueUpdate,
            label = label,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            prefix = prefix,
            suffix = suffix,
            placeholder = placeholder,
            singleLine = true,
            isError = isError,
            supportingText = {
                if (isError) {
                    Text(
                        text = "Invalid Input!",
                        color = Color.Red
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),

        )
    }
}