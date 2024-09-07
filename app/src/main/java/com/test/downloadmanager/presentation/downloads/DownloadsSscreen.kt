package com.test.downloadmanager.presentation.downloads

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.test.downloadmanager.presentation.components.MediumTextView
import com.test.downloadmanager.presentation.components.VerticalSpace

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DownloadsScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {

        MediumTextView(
            text = "Downloads",
            fontWeight = FontWeight.Bold,
            fontSize = 18
        )
        VerticalSpace()

        val permissions by remember {
            mutableStateOf(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    listOf(
                        Manifest.permission.READ_MEDIA_VIDEO,
                        Manifest.permission.READ_MEDIA_IMAGES,
                    )
                } else {
                    listOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                }
            )
        }

        val checkPermission = rememberMultiplePermissionsState(permissions = permissions)
        if (checkPermission.allPermissionsGranted) {
            DownloadsScreenContent()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                MediumTextView(text = "Permissions are required to show Downloads")
                VerticalSpace()
                Button(
                    modifier = Modifier
                        .width(120.dp),
                    onClick = {
                        checkPermission.launchMultiplePermissionRequest()
                    }
                ) {
                    MediumTextView(text = "Allow")
                }
            }
        }
    }

}