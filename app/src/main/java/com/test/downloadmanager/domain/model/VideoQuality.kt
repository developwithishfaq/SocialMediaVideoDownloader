package com.test.downloadmanager.domain.model

data class VideoQuality(
    val qualityUrl: String,
    val qualityName: String,
    val qualityDesc: String,
) {
    var isSelected: Boolean = false
}
