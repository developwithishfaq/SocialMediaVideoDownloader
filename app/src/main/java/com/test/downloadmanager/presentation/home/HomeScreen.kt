package com.test.downloadmanager.presentation.home

import android.Manifest
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.test.downloadmanager.R
import com.test.downloadmanager.core.LoadingState
import com.test.downloadmanager.presentation.components.HorizontalSpace
import com.test.downloadmanager.presentation.components.MainButton
import com.test.downloadmanager.presentation.components.OutlinedTextFieldWithRedBorder
import com.test.downloadmanager.presentation.components.SmallTextView
import com.test.downloadmanager.presentation.components.VerticalSpace
import com.test.downloadmanager.presentation.components.getColor
import com.test.downloadmanager.presentation.home.components.QualityItem
import com.test.downloadmanager.presentation.home.events.HomeScreenEvents
import org.koin.androidx.compose.koinViewModel

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class,
    ExperimentalPermissionsApi::class
)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val permissionsState =
            rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
        LaunchedEffect(permissionsState.status.isGranted.not()) {
            if (permissionsState.status.isGranted.not()) {
                permissionsState.launchPermissionRequest()
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        if (state.parseState is LoadingState.Loading || state.parseState is LoadingState.Success) {
            ModalBottomSheet(
                onDismissRequest = {
                    viewModel.onEvent(HomeScreenEvents.DismissSheet)
                },
            ) {
                Column(
                    modifier = Modifier
                        .padding(
                            vertical = 15.dp,
                            horizontal = 6.dp
                        )
                ) {
                    if (state.parseState is LoadingState.Loading) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(color = R.color.mainColor.getColor())
                            VerticalSpace(8)
                            SmallTextView(text = "Parsing Url", fontSize = 14)
                            VerticalSpace(20)
                        }
                    } else {
                        val videoModel = state.parseState.data
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(60.dp)
                                        .background(
                                            R.color.grey.getColor(),
                                            RoundedCornerShape(12.dp)
                                        )
                                ) {
                                    GlideImage(
                                        model = videoModel?.thumbnail,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxSize()
                                    )
                                }
                                HorizontalSpace()
                                SmallTextView(text = videoModel?.title ?: "Unknown", fontSize = 14)
                            }
                            VerticalSpace()
                            Row(
                                modifier = Modifier
                                    .horizontalScroll(rememberScrollState())
                            ) {
                                videoModel?.qualities?.forEach { quality ->
                                    QualityItem(
                                        title = quality.qualityName,
                                        desc = quality.qualityDesc,
                                        isSelected = state.selectedQuality == quality
                                    ) {
                                        viewModel.onEvent(HomeScreenEvents.SelectQuality(quality))
                                    }
                                }
                            }
                            VerticalSpace()
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                MainButton(
                                    text = "Cancel",
                                    bgColor = R.color.grey,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 8.dp)
                                ) {
                                    viewModel.onEvent(HomeScreenEvents.DismissSheet)
                                }
                                MainButton(
                                    text = "Download",
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 8.dp)
                                ) {
                                    viewModel.onEvent(HomeScreenEvents.StartDownloading)
                                }
                            }
                            VerticalSpace()
                        }
                    }
                }
            }
        }

        OutlinedTextFieldWithRedBorder(
            text = state.url,
            onValueChange = {
                viewModel.onEvent(HomeScreenEvents.Url(it))
            }
        )
        MainButton(text = "Parse Link") {
            viewModel.onEvent(HomeScreenEvents.StartParsing)
        }


    }
}