package com.test.downloadmanager.domain.usecases

import android.content.Context
import com.test.downloadmanager.data.dto.toInProgressUiModel
import com.test.downloadmanager.data.local.InProgressDao
import com.test.downloadmanager.data.model.InProgressVideo
import com.test.downloadmanager.domain.model.InProgressUiModel
import com.test.downloadmanager.domain.repository.MediaDownloader
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GetAllInProgressVideosUseCase(
    private val inProgressDao: InProgressDao,
    private val downloader: MediaDownloader
) {
    private val _inProgressItems = MutableStateFlow<List<InProgressUiModel>>(emptyList())
    val inProgressItems = _inProgressItems.asStateFlow()


    suspend operator fun invoke() {
        val allVideos: List<InProgressVideo> = inProgressDao.getAllInProgressVideos().filter {
            it.isDownloaded.not()
        }
        val list = allVideos.mapNotNull { video ->
            val downloadedInfo = downloader.getDownloadInfoById(video.id)
            downloadedInfo?.toInProgressUiModel(video)
        }
        _inProgressItems.update {
            list
        }
    }

}