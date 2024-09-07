package com.test.downloadmanager.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun OutlinedTextFieldWithRedBorder(
    text: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = text, // Your text state here
        onValueChange = onValueChange, // Your value change handler here
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Red, RoundedCornerShape(10.dp)) // Red border
            .padding(horizontal = 8.dp, vertical = 10.dp), // Optional padding inside the text field
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent, // Transparent background
            unfocusedContainerColor = Color.Transparent, // Transparent background
            focusedIndicatorColor = Color.Transparent, // No indicator when focused
            unfocusedIndicatorColor = Color.Transparent // No indicator when unfocused
        )
    )
}