package com.rpn.iiucstudy.presentation.ui.course_details.component

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.rpn.iiucstudy.presentation.icon.AppIcons
import org.jetbrains.compose.resources.painterResource

@Composable
fun CourseImage(url: String, modifier: Modifier, contentScale: ContentScale) {
    AsyncImage(
        modifier = modifier
            .background(shape = MaterialTheme.shapes.small, color = Color.Gray),
        model = url,
        error = painterResource(AppIcons.Logo),
        contentScale = contentScale,
        contentDescription = null
    )
}