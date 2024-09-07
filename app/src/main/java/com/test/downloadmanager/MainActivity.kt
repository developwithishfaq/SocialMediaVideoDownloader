package com.test.downloadmanager

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.test.downloadmanager.presentation.components.SmallTextView
import com.test.downloadmanager.presentation.components.VerticalSpace
import com.test.downloadmanager.presentation.components.getColor
import com.test.downloadmanager.presentation.downloads.DownloadsScreen
import com.test.downloadmanager.presentation.home.HomeScreen
import com.test.downloadmanager.presentation.inProgress.InProgressScreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class, ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            val pagerState = rememberPagerState {
                3
            }
            var selectedItem by remember {
                mutableStateOf(0)
            }
            LaunchedEffect(selectedItem) {
                if (selectedItem != pagerState.currentPage) {
                    pagerState.animateScrollToPage(selectedItem)
                }
            }

            Scaffold(
                bottomBar = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        BottomNavItem(
                            icon = R.drawable.ic_home,
                            text = "Home",
                            isSelected = selectedItem == 0,
                            onClick = {
                                selectedItem = 0
                            }
                        )
                        BottomNavItem(
                            icon = R.drawable.ic_in_progress,
                            text = "In Progress",
                            isSelected = selectedItem == 1,
                            onClick = {
                                selectedItem = 1
                            }
                        )
                        BottomNavItem(
                            icon = R.drawable.ic_downloaded,
                            text = "Downloads",
                            isSelected = selectedItem == 2,
                            onClick = {
                                selectedItem = 2
                            }
                        )
                    }
                }
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .padding(it)
                ) { page ->
                    when (page) {
                        0 -> {
                            HomeScreen()
                        }

                        1 -> {
                            InProgressScreen()
                        }

                        2 -> {
                            DownloadsScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.BottomNavItem(
    icon: Int,
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val color = if (isSelected) {
        R.color.black
    } else {
        R.color.grey
    }
    Column(
        modifier = Modifier
            .weight(1f)
            .clickable {
                onClick.invoke()
            }
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp),
            colorFilter = ColorFilter.tint(color.getColor())
        )
        VerticalSpace(6)
        SmallTextView(
            text = text,
            fontWeight = if (isSelected) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            },
            color = color
        )

    }
}