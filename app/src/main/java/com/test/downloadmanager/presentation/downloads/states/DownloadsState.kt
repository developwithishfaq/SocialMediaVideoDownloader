package com.test.downloadmanager.presentation.downloads.states

import com.test.downloadmanager.core.LoadingState
import com.test.downloadmanager.data.model.DownloadedVideoModel

data class DownloadsState(
    val downloads: LoadingState<List<DownloadedVideoModel>> = LoadingState.Loading()
)
