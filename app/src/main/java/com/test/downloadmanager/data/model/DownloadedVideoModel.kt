package com.test.downloadmanager.data.model

import android.net.Uri

data class DownloadedVideoModel(
    val filePath: String,
    val fileUri: Uri?,
    val fileName: String,
    val fileSize: Long,
)