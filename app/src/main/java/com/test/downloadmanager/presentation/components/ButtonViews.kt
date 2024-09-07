package com.test.downloadmanager.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.test.downloadmanager.R

@Composable
fun MainButton(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Int = R.color.white,
    bgColor: Int = R.color.mainColor,
    roundness: Int = 10,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = bgColor.getColor()
        ),
        shape = RoundedCornerShape(roundness.dp)
    ) {
        MediumTextView(
            text = text,
            color = textColor
        )
    }
}


