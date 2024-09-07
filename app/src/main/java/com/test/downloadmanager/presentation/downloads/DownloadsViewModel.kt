package com.test.downloadmanager.presentation.downloads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.downloadmanager.core.LoadingState
import com.test.downloadmanager.domain.usecases.GetAllDownloads
import com.test.downloadmanager.presentation.downloads.states.DownloadsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DownloadsViewModel(
    private val getAllDownloads: GetAllDownloads
) : ViewModel() {

    private val _state = MutableStateFlow(DownloadsState())
    val state = _state.asStateFlow()

    init {
        fetchDownloads()
    }

    fun fetchDownloads() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    downloads = LoadingState.Loading()
                )
            }
            val list = getAllDownloads()
            _state.update {
                it.copy(
                    downloads = if (list.isEmpty()) {
                        LoadingState.Failure("No downloads found")
                    } else {
                        LoadingState.Success(list)
                    }
                )
            }
        }
    }
}