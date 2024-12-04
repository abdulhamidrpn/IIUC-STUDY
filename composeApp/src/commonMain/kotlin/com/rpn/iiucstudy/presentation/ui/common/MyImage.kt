package com.rpn.iiucstudy.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.rpn.iiucstudy.presentation.theme.getDimens
import iiuc_study.composeapp.generated.resources.Res
import iiuc_study.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource

@Composable
fun MyImage(url: String, modifier: Modifier) {
    AsyncImage(
        modifier = modifier
            .size(getDimens().imageHeight)
            .background(Color.Gray),
        model = url,
        error = painterResource(Res.drawable.logo),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}