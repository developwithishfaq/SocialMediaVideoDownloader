package com.test.downloadmanager.domain.repository

import java.io.File

interface StorageRepository {
    suspend fun getFilesFromPath(path: String): List<File>
}