package com.test.downloadmanager.domain.usecases

import android.content.Context
import android.media.MediaScannerConnection
import android.os.Environment
import android.util.Log
import com.test.downloadmanager.core.getVideoActualUri
import com.test.downloadmanager.data.model.DownloadedVideoModel
import com.test.downloadmanager.domain.repository.StorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class GetAllDownloads(
    private val context: Context,
    private val storageRepository: StorageRepository
) {
    suspend operator fun invoke(): List<DownloadedVideoModel> {
        return withContext(Dispatchers.IO) {
            val filesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path + "/SocialMediaDownloader"
            val files = storageRepository.getFilesFromPath(filesDir)
            Log.d("cvv", "invoke: ${filesDir}")
            Log.d("cvv", "invoke: Size=${files.size}")
            val list = files.map { file ->
                MediaScannerConnection.scanFile(
                    context,
                    arrayOf(file.path),
                    arrayOf("video/*"),
                    null
                )
                async {
                    val uri = file.path.getVideoActualUri(contentResolver = context.contentResolver)
                    DownloadedVideoModel(
                        fileName = file.name,
                        filePath = file.path,
                        fileUri = uri,
                        fileSize = file.length()
                    )
                }
            }.awaitAll()
            list
        }
    }
}