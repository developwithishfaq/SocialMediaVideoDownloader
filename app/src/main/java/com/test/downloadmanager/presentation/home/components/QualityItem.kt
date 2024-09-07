package com.test.downloadmanager.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.test.downloadmanager.R
import com.test.downloadmanager.presentation.components.SmallTextView
import com.test.downloadmanager.presentation.components.VerticalSpace
import com.test.downloadmanager.presentation.components.getColor

@Composable
fun QualityItem(
    title: String,
    desc: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 6.dp, vertical = 6.dp)
            .border(
                width = 1.dp,
                color = if (isSelected) {
                    R.color.mainColor.getColor()
                } else {
                    R.color.white.getColor()
                },
                shape = RoundedCornerShape(10.dp)
            )
            .background(R.color.white.getColor())
            .clickable {
                onClick.invoke()
            }
            .padding(horizontal = 10.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SmallTextView(
            text = title, fontSize = 14,
            fontWeight = FontWeight.Bold
        )
        VerticalSpace(8)
        SmallTextView(
            text = desc, fontSize = 13,
            fontWeight = FontWeight.Normal
        )
    }
}