package com.test.downloadmanager.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.test.downloadmanager.R


@Composable
fun SmallTextView(
    text: String,
    color: Int = R.color.black,
    fontSize: Int = 12,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = text,
        color = color.getColor(),
        fontSize = fontSize.sp,
        style = TextStyle(fontWeight = fontWeight)
    )
}

@Composable
fun MediumTextView(
    text: String,
    color: Int = R.color.black,
    fontSize: Int = 16,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = text,
        color = color.getColor(),
        fontSize = fontSize.sp,
        style = TextStyle(fontWeight = fontWeight)
    )
}
