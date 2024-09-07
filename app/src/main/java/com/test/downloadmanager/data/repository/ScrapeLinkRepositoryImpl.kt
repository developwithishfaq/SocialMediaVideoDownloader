package com.test.downloadmanager.data.repository

import com.example.socialmediadownloader.EasyDownloader
import com.example.socialmediadownloader.data.response.NetworkResponse
import com.example.socialmediadownloader.domain.model.Video
import com.test.downloadmanager.core.LoadingState
import com.test.downloadmanager.domain.repository.ScrapeLinkRepository

class ScrapeLinkRepositoryImpl(
    private val easyDownloader: EasyDownloader
) : ScrapeLinkRepository {
    override suspend fun scrapeLink(url: String): LoadingState<Video> {
        return when (val response = easyDownloader.downloadVideo(url)) {
            is NetworkResponse.Failure -> {
                LoadingState.Failure(response.error)
            }

            is NetworkResponse.Idle -> {
                LoadingState.Idle()

            }

            is NetworkResponse.Loading -> {
                LoadingState.Loading()
            }

            is NetworkResponse.Success -> {
                response.data?.let {
                    LoadingState.Success(it)
                } ?: run {
                    LoadingState.Failure("No Data Found")
                }
            }
        }
    }
}