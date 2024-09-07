package com.test.downloadmanager.data.repository

import android.util.Log
import com.test.downloadmanager.domain.repository.StorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class StorageRepositoryImpl : StorageRepository {
    override suspend fun getFilesFromPath(path: String): List<File> {
        return withContext(Dispatchers.IO) {
            val filesList = File(path).listFiles()?.toList() ?: listOf()
            Log.d("cvv","filesList: ${File(path).exists()}")
            Log.d("cvv","filesList: ${File(path).listFiles()}")
            filesList.filter {
                it.exists() && it.length() > 0
            }
        }
    }
}