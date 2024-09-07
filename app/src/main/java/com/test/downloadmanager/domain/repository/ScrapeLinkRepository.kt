package com.test.downloadmanager.domain.repository

import com.example.socialmediadownloader.data.response.NetworkResponse
import com.example.socialmediadownloader.domain.model.Video
import com.test.downloadmanager.core.LoadingState

interface ScrapeLinkRepository {

    suspend fun scrapeLink(url: String): LoadingState<Video>
}