package com.test.downloadmanager.presentation.home.state

import com.test.downloadmanager.core.LoadingState
import com.test.downloadmanager.domain.model.VideoMainModel
import com.test.downloadmanager.domain.model.VideoQuality

data class HomeScreenState(
    val parseState: LoadingState<VideoMainModel> = LoadingState.Idle(),
    val selectedQuality: VideoQuality? = null,
    val url: String = ""
)