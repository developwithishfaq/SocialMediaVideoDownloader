package com.test.downloadmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InProgressVideo(
    @PrimaryKey
    val id: Long,
    val title: String,
    val thumbnail: String,
    val mediaUrl: String,
    val downloadedBytes: Long,
    val totalSize: Long,
    val isDownloaded: Boolean,
    val isCancelled: Boolean,
    val isFailed: Boolean,
    val isInPause: Boolean,
)
