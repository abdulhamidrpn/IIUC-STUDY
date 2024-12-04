package com.rpn.iiucstudy

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.rpn.iiucstudy.di.initKoin
import iiuc_study.composeapp.generated.resources.Res
import iiuc_study.composeapp.generated.resources.logo

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "IIUC Study",
        state = WindowState(
            width = 1280.dp,
            height = 720.dp,
            position = WindowPosition(Alignment.Center)
        ),
        icon = org.jetbrains.compose.resources.painterResource(Res.drawable.logo)
    ) {
        window.minimumSize = window.size
        App()
    }
}