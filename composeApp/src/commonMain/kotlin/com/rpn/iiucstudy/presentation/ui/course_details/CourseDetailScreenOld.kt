package com.rpn.iiucstudy.presentation.ui.course_details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.PlaylistPlay
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.rpn.iiucstudy.domain.model.Course
import com.rpn.iiucstudy.presentation.utils.generateRandomSaturatedColor
import iiuc_study.composeapp.generated.resources.Res
import iiuc_study.composeapp.generated.resources.go_back
import org.jetbrains.compose.resources.stringResource

@Composable
fun CourseDetailScreenOld(
    state: CourseDetailScreenState,
    currentCourse: Course,
    onBackClick: () -> Unit
) {
    var showPlaylist by remember { mutableStateOf(false) } // Toggle for YouTube playlist side menu
    BackgroundImageWithOverlay()
    // Main background image with black overlay
    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top right buttons for Favorites and Playlist
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                Row {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.go_back),
                            tint = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(onClick = { /* Add to favorites */ }) {
                        Icon(
                            Icons.Default.FavoriteBorder,
                            contentDescription = "Add to Favorites",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { showPlaylist = !showPlaylist }) {
                        Icon(
                            Icons.Default.PlaylistPlay,
                            contentDescription = "Show Playlist",
                            tint = Color.White
                        )
                    }
                }
            }

            // Course Title and Description
            Text(
                text = currentCourse.subjectCode,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = currentCourse.title,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = currentCourse.description,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(0.9f)
            )
            Text(
                text = "See More.",
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clickable { /* See more action */ }
            )
            Spacer(modifier = Modifier.weight(1f))

            // Bottom Glossy Section with 4 Clickable Rows
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Center)
                ) {
                    // Next Course Button
                    Button(
                        onClick = { /* Navigate to next course */ },
                        colors = ButtonDefaults.buttonColors(Color.Gray)
                    ) {
                        Text(text = "Next Course", fontSize = 18.sp, color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ClickableOption(
                            icon = Icons.Default.PlayCircle,
                            label = "Course Video",
                            onClick = { /* Navigate to Course Video */ }
                        )
                        ClickableOption(
                            icon = Icons.Default.Cloud,
                            label = "Resources",
                            onClick = { /* Navigate to Resources */ }
                        )
                        ClickableOption(
                            icon = Icons.Default.Assignment,
                            label = "Syllabus",
                            onClick = { /* Navigate to Syllabus */ }
                        )
                        ClickableOption(
                            icon = Icons.Default.Help,
                            label = "Question Paper",
                            onClick = { /* Navigate to Question Paper */ }
                        )
                    }


                }
            }
        }

        // YouTube Playlist Side Menu
        if (showPlaylist) {
            SideMenu {
                Text(
                    "YouTube Playlist",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )
                // Placeholder for playlist content
            }
        }
    }
}

@Composable
fun BackgroundImageWithOverlay() {
    val painter =
        rememberAsyncImagePainter("https://images.unsplash.com/photo-1526665814499-fbdb4394ef3e?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D") // Random background image
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
    // Black overlay to enhance text visibility
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
    )
}


@Composable
fun ClickableOption(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit
) {


    val departmentColor = generateRandomSaturatedColor()// semester.color.toColor()
    OutlinedCard(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .clickable { onClick() },
        border = BorderStroke(2.dp, departmentColor),
        colors = CardDefaults.cardColors(containerColor = departmentColor.copy(alpha = 0.2f))
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = label, modifier = Modifier.size(36.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}


@Composable
fun SideMenu(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(250.dp)
            .background(Color.Black.copy(alpha = 0.9f))
            .padding(16.dp)
    ) {
        content()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailScreenOld(
    state: CourseDetailScreenState,
    onEvent: (CourseDetailScreenEvents) -> Unit,
    currentCourse: Course,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
    ) {
        // Content inside the Scaffold
        state.courseVideo.DisplayResult(
            onIdle = {
                Text(text = "Idle")
            },
            onLoading = {
                Text(text = "Loading...")
            },
            onSuccess = { videoList ->
                CourseDetailScreenOld(
                    state = state,
                    currentCourse = currentCourse,
                    onBackClick = onBackClick
                )
                /*YouTubeUI(
                    currentVideo = state.currentVideo,
                    videoList = videoList,
                    onVideoClick = {
                        onEvent(CourseDetailScreenEvents.SetCurrentVideo(it))
                    }
                )*/
            },
            onError = {
                Text(text = "Error: $it")
            }
        )
    }

}

