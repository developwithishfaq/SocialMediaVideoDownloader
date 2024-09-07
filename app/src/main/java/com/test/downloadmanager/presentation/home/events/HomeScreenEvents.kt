package com.test.downloadmanager.presentation.home.events

import com.test.downloadmanager.domain.model.VideoQuality

sealed class HomeScreenEvents {
    data class Url(val text: String) : HomeScreenEvents()
    data object StartParsing : HomeScreenEvents()
    data class SelectQuality(val quality: VideoQuality?) : HomeScreenEvents()
    data object StartDownloading : HomeScreenEvents()
    data object DismissSheet : HomeScreenEvents()
}