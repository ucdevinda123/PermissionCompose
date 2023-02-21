package com.spore.permissioncompose.composable

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermission(permission: String) {

    val permissionState = rememberPermissionState(permission = permission)
    when (permissionState.status) {

        is PermissionStatus.Granted -> {
            Log.d("PERMISSION-STATUS", "GRANTED")
            GrantedContent()
        }

        is PermissionStatus.Denied -> {
            DenyContent(onRequestPermission = {
                permissionState.launchPermissionRequest()
            })
        }
    }
}

@Composable
fun GrantedContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Permission Is Granted")

    }
}


@Composable
fun DenyContent(onRequestPermission: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { onRequestPermission() }) {
            Text(text = "REQUEST Permission")
        }
    }
}