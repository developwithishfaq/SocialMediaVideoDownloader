package com.test.downloadmanager.presentation.downloads.component

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.test.downloadmanager.R
import com.test.downloadmanager.data.model.DownloadedVideoModel
import com.test.downloadmanager.presentation.components.HorizontalSpace
import com.test.downloadmanager.presentation.components.SmallTextView
import com.test.downloadmanager.presentation.components.VerticalSpace
import com.test.downloadmanager.presentation.components.getColor

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DownloadsItem(
    downloadedVideoModel: DownloadedVideoModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                        model = downloadedVideoModel.filePath,
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
                    SmallTextView(text = downloadedVideoModel.fileName)
                    VerticalSpace()
                    SmallTextView(text = downloadedVideoModel.filePath, fontSize = 10)
                }
            }
        }
        VerticalSpace()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(R.color.grey.getColor())
        )
    }
}