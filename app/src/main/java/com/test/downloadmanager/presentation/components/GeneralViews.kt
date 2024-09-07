package com.test.downloadmanager.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.test.downloadmanager.R


@Composable
fun VerticalSpace(dp: Int = 10) {
    Spacer(modifier = Modifier.height(dp.dp))
}

@Composable
fun HorizontalSpace(dp: Int = 10) {
    Spacer(modifier = Modifier.width(dp.dp))
}

@Composable
fun Int.getColor() = colorResource(id = this)

@Composable
fun LoadingBox(text: String = "Please Wait") {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            CircularProgressIndicator(color = R.color.mainColor.getColor())
            if (text.isNotBlank()) {
                VerticalSpace(dp = 10)
                SmallTextView(text = text, fontWeight = FontWeight.Bold)
            }
        }
    }
}