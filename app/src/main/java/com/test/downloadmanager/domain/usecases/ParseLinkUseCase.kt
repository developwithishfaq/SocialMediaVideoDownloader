package com.test.downloadmanager.domain.usecases

import com.test.downloadmanager.core.LoadingState
import com.test.downloadmanager.data.dto.toVideoMainModel
import com.test.downloadmanager.domain.model.VideoMainModel
import com.test.downloadmanager.domain.repository.ScrapeLinkRepository

class ParseLinkUseCase(
    private val repository: ScrapeLinkRepository
) {
    suspend operator fun invoke(link: String): LoadingState<VideoMainModel> {
        return when (val response = repository.scrapeLink(link)) {
            is LoadingState.Failure -> {
                LoadingState.Failure(response.error)
            }

            is LoadingState.Idle -> {
                LoadingState.Idle()
            }

            is LoadingState.Loading -> {
                LoadingState.Loading()
            }

            is LoadingState.Success -> {
                response.data?.let {
                    LoadingState.Success(it.toVideoMainModel())
                } ?: run {
                    LoadingState.Failure("No Videos Found")
                }
            }
        }
    }
}