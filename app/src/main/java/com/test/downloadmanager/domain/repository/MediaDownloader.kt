package com.test.downloadmanager.domain.repository

import com.test.downloadmanager.data.model.DownloadInfo

interface MediaDownloader {
    fun downloadMedia(
        url: String,
        fileName: String,
        extension: String,
    ): Long

    suspend fun getDownloadInfoById(id: Long): DownloadInfo?
}