package com.test.downloadmanager.data.repository

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import com.test.downloadmanager.data.model.DownloadInfo
import com.test.downloadmanager.domain.repository.MediaDownloader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MediaDownloaderImpl(
    private val context: Context
) : MediaDownloader {

    private val downloadManager =
        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager


    override fun downloadMedia(url: String, fileName: String, extension: String): Long {
        val request = DownloadManager.Request(url.toUri())
            .setTitle("$fileName.$extension")
            .setMimeType("video/*")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "SocialMediaDownloader/${fileName.replace(":", "")}.$extension"
            )
        return downloadManager.enqueue(request)
    }

    override suspend fun getDownloadInfoById(id: Long): DownloadInfo? {
        return withContext(Dispatchers.IO) {
            val query = DownloadManager.Query()
                .setFilterById(id)
            val cursor = downloadManager.query(query)
            if (cursor.moveToFirst()) {
                val status =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS))

                val downloadInfo = DownloadInfo()

                val bytesDownloaded =
                    cursor.getLong(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                val totalSize =
                    cursor.getLong(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))

                downloadInfo.bytesDownloaded = bytesDownloaded
                downloadInfo.totalSize = totalSize
                downloadInfo.id = id
                when (status) {
                    DownloadManager.STATUS_SUCCESSFUL -> {
                        downloadInfo.isDownloaded = true
                    }

                    DownloadManager.STATUS_FAILED -> {
                        downloadInfo.isFailed = true
                    }

                    DownloadManager.STATUS_RUNNING -> {
                        downloadInfo.isInPause = false
                    }

                    DownloadManager.STATUS_PAUSED -> {
                        downloadInfo.isInPause = true
                    }
                }
                cursor.close()
                downloadInfo
            } else {
                null
            }
        }
    }
}







