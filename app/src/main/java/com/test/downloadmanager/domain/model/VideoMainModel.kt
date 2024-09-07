package com.test.downloadmanager.domain.model

data class VideoMainModel(
    val title: String,
    val thumbnail: String,
    val qualities: List<VideoQuality>
)