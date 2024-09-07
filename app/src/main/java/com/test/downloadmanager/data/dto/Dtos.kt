package com.test.downloadmanager.data.dto

import com.example.socialmediadownloader.domain.model.Video
import com.test.downloadmanager.data.model.DownloadInfo
import com.test.downloadmanager.data.model.InProgressVideo
import com.test.downloadmanager.domain.model.InProgressUiModel
import com.test.downloadmanager.domain.model.VideoMainModel
import com.test.downloadmanager.domain.model.VideoQuality


fun Video.toVideoMainModel() = VideoMainModel(
    title = this.title,
    thumbnail = this.quality.getOrNull(0)?.qualityUrl ?: "",
    qualities = this.quality.map { quality ->
        VideoQuality(
            qualityUrl = quality.qualityUrl,
            qualityName = quality.qualityName,
            qualityDesc = quality.qualitySize
        )
    }
)

fun DownloadInfo.toInProgressUiModel(inProgressVideo: InProgressVideo): InProgressUiModel {
    return InProgressUiModel(
        id = inProgressVideo.id,
        title = inProgressVideo.title,
        thumbnail = inProgressVideo.thumbnail,
        mediaUrl = inProgressVideo.mediaUrl,
        bytesDownloaded = this.bytesDownloaded,
        totalSize = this.totalSize,
        isDownloaded = this.isDownloaded,
        isCancelled = this.isCancelled,
        isFailed = this.isFailed,
        isInPause = this.isInPause
    )
}