package com.test.downloadmanager.core

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


suspend fun String.getVideoActualUri(contentResolver: ContentResolver): Uri? {
    return withContext(Dispatchers.IO) {
        val uri = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
            }

            else -> {
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            }
        }
        contentResolver.query(
            uri,
            arrayOf(MediaStore.Video.Media._ID),
            "${MediaStore.Video.Media.DATA} = ?",
            arrayOf(this@getVideoActualUri),
            null
        )?.use { cursor ->
            if (cursor.moveToFirst()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID))
                val contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                contentUri.buildUpon().appendPath(id.toString()).build()
            } else {
                null
            }
        }
    }
}

fun playVideo(uri: Uri, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, uri)
    intent.setDataAndType(uri, "video/mp4")
    context.startActivity(intent)
}