package com.test.downloadmanager.presentation.inProgress.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.test.downloadmanager.R
import com.test.downloadmanager.domain.model.InProgressUiModel
import com.test.downloadmanager.presentation.components.HorizontalSpace
import com.test.downloadmanager.presentation.components.SmallTextView
import com.test.downloadmanager.presentation.components.VerticalSpace
import com.test.downloadmanager.presentation.components.getColor

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun InProgressItem(progress: InProgressUiModel) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(60.dp)
                    .background(color = R.color.grey.getColor(), RoundedCornerShape(10.dp))
            ) {
                GlideImage(
                    model = progress.thumbnail,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            HorizontalSpace()
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                SmallTextView(text = progress.title)
                val percentage =
                    ((progress.bytesDownloaded * 100) / progress.totalSize).toFloat() / 100
                VerticalSpace()
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth(),
                    progress = {
                        percentage
                    },
                    color = R.color.mainColor.getColor()
                )
            }

        }
        if (progress.isInPause) {
            VerticalSpace()
            SmallTextView(text = "Waiting In Que")
        } else if (progress.isCancelled) {
            VerticalSpace()
            SmallTextView(text = "Cancelled", color = R.color.mainColor)
        }
    }
}