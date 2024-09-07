package com.test.downloadmanager.presentation.inProgress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.downloadmanager.domain.usecases.GetAllInProgressVideosUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class InProgressViewModel(
    private val getAllInVideo: GetAllInProgressVideosUseCase
) : ViewModel() {

    val state = getAllInVideo.inProgressItems.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        emptyList()
    )

    init {
        viewModelScope.launch {
            while (true) {
                delay(500)
                getAllInVideo()
            }
        }
    }


}