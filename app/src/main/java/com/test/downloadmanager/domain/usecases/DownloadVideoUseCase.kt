package com.test.downloadmanager.domain.usecases

import com.test.downloadmanager.data.local.InProgressDao
import com.test.downloadmanager.data.model.InProgressVideo
import com.test.downloadmanager.domain.model.VideoMainModel
import com.test.downloadmanager.domain.model.VideoQuality
import com.test.downloadmanager.domain.repository.MediaDownloader

class DownloadVideoUseCase(
    private val mediaDownloader: MediaDownloader,
    private val dao: InProgressDao
) {

    suspend operator fun invoke(
        videoMainModel: VideoMainModel,
        qualities: List<VideoQuality>
    ) {
        qualities.forEach { quality ->
            val name = videoMainModel.title + "_${quality.qualityName}"
            val downloadId = mediaDownloader.downloadMedia(
                url = quality.qualityUrl,
                fileName = name,
                extension = "mp4"
            )
            dao.addInProgress(
                InProgressVideo(
                    id = downloadId,
                    title = name,
                    thumbnail = videoMainModel.thumbnail,
                    mediaUrl = quality.qualityUrl,
                    downloadedBytes = 0,
                    totalSize = 0,
                    isDownloaded = false,
                    isCancelled = false,
                    isFailed = false,
                    isInPause = false,

                    )
            )
        }
    }
}