package com.test.downloadmanager.presentation.inProgress

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.test.downloadmanager.presentation.components.MediumTextView
import com.test.downloadmanager.presentation.components.VerticalSpace
import com.test.downloadmanager.presentation.inProgress.components.InProgressItem
import org.koin.androidx.compose.koinViewModel


@Composable
fun InProgressScreen(
    viewModel: InProgressViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {

        MediumTextView(
            text = "In Progress",
            fontWeight = FontWeight.Bold,
            fontSize = 18
        )
        VerticalSpace()
        val list = state.filter {
            if (it.bytesDownloaded > 0) {
                it.bytesDownloaded < it.totalSize
            }else{
                true
            }
        }
        if (list.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                MediumTextView(text = "No Videos is in Progress")
            }
        } else {
            LazyColumn {
                items(list) { progress ->
                    InProgressItem(progress)
                }
            }
        }
    }


}

