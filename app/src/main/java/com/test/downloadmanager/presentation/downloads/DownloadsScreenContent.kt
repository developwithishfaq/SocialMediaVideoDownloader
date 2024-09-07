package com.test.downloadmanager.presentation.downloads

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.test.downloadmanager.core.LoadingState
import com.test.downloadmanager.core.playVideo
import com.test.downloadmanager.presentation.components.LoadingBox
import com.test.downloadmanager.presentation.components.MediumTextView
import com.test.downloadmanager.presentation.downloads.component.DownloadsItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun DownloadsScreenContent(
    viewModel: DownloadsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    DisposableEffect(Unit) {
        viewModel.fetchDownloads()
        onDispose {
        }
    }

    when (state.downloads) {
        is LoadingState.Failure -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                MediumTextView(text = state.downloads.error ?: "")
            }
        }

        is LoadingState.Idle -> {

        }

        is LoadingState.Loading -> {
            LoadingBox(text = "Fetching")
        }

        is LoadingState.Success -> {
            val context = LocalContext.current
            LazyColumn {
                items(state.downloads.data ?: emptyList()) { video ->
                    DownloadsItem(
                        video,
                        modifier = Modifier
                            .clickable {
                                video.fileUri?.let { playVideo(it, context) }
                            }
                    )
                }
            }
        }
    }


}