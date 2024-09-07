package com.test.downloadmanager.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.downloadmanager.core.LoadingState
import com.test.downloadmanager.domain.usecases.DownloadVideoUseCase
import com.test.downloadmanager.domain.usecases.ParseLinkUseCase
import com.test.downloadmanager.presentation.home.events.HomeScreenEvents
import com.test.downloadmanager.presentation.home.state.HomeScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val parseLinkUseCase: ParseLinkUseCase,
    private val downloadVideoUseCase: DownloadVideoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()


    fun onEvent(events: HomeScreenEvents) {
        when (events) {
            is HomeScreenEvents.SelectQuality -> {
                _state.update {
                    it.copy(
                        selectedQuality = events.quality
                    )
                }
            }

            HomeScreenEvents.StartDownloading -> {
                startDownloading()
            }

            HomeScreenEvents.StartParsing -> {
                startParsing()
            }

            is HomeScreenEvents.Url -> {
                _state.update {
                    it.copy(
                        url = events.text
                    )
                }
            }

            HomeScreenEvents.DismissSheet -> {
                _state.update {
                    it.copy(
                        parseState = LoadingState.Idle()
                    )
                }
            }
        }
    }

    private fun startDownloading() {
        if (state.value.selectedQuality != null) {
            val videoModel = state.value.parseState.data
            if (videoModel != null) {
                viewModelScope.launch {
                    downloadVideoUseCase(
                        videoMainModel = videoModel,
                        qualities = listOf(state.value.selectedQuality!!)
                    )
                    _state.update {
                        it.copy(
                            parseState = LoadingState.Idle()
                        )
                    }
                }
            }
        }
    }

    private fun startParsing() {
        viewModelScope.launch {
            if (state.value.url.isNotBlank()) {
                _state.update {
                    it.copy(
                        parseState = LoadingState.Loading()
                    )
                }
                val response = parseLinkUseCase(state.value.url)
                _state.update {
                    it.copy(
                        parseState = response
                    )
                }

            }
        }
    }


}