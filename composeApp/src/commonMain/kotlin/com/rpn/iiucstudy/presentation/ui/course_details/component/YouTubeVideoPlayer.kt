package com.rpn.iiucstudy.presentation.ui.course_details.component

//import VideoPlayer
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rpn.iiucstudy.VideoPlayer
import com.rpn.iiucstudy.domain.model.CourseVideo
import com.rpn.iiucstudy.presentation.utils.formatDateTime
import com.rpn.iiucstudy.presentation.utils.generateRandomSaturatedColor


@Composable
fun YouTubeUI(
    currentVideo: CourseVideo?,
    videoList: List<CourseVideo>,
    onVideoClick: (CourseVideo) -> Unit
) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp).fillMaxSize(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Left: Video Player Section
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(2f) // Takes 2/3rd of the screen width
                .fillMaxHeight()
        ) {
            VideoPlayer(
                modifier = Modifier
                    .aspectRatio(16f / 9f) // Setting the aspect ratio to 16:9
                    .fillMaxWidth(), // The box will take full width and adjust the height accordingly
                url = "https://www.youtube.com/watch?v=" + currentVideo?.videoId,
                thumbnail = currentVideo?.thumbnailsStandard
            )
            Spacer(modifier = Modifier.height(16.dp))
            currentVideo?.videoTitle?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            currentVideo?.videoDescription?.let { Text(text = it) }
            Spacer(modifier = Modifier.height(8.dp))

        }

        // Right: List of Videos
        LazyColumn(
            modifier = Modifier
                .weight(1f) // Takes 1/3rd of the screen width
                .fillMaxHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(videoList) { video ->
                VideoListItem(
                    video,
                    selected = video == currentVideo,
                    onVideoClick = onVideoClick
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VideoListItem(video: CourseVideo, selected: Boolean, onVideoClick: (CourseVideo) -> Unit) {

    val departmentColor = generateRandomSaturatedColor()
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(MaterialTheme.shapes.small)
            .clickable {
                onVideoClick(video)
            },
        border = if (selected) BorderStroke(2.dp, departmentColor) else BorderStroke(
            0.dp,
            Color.Transparent
        ),
        colors = if (selected) CardDefaults.cardColors(containerColor = departmentColor.copy(alpha = 0.2f))
        else CardDefaults.cardColors()
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Video Thumbnail
            CourseImage(
                url = video.thumbnailsDefault,
                modifier = Modifier
                    .size(80.dp)
                    .aspectRatio(16 / 9f),
                contentScale = ContentScale.Crop
            )
            // Video Title and Published Date
            Column {
                Text(
                    text = video.videoTitle,
                    maxLines = 2,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Published: ${video.publishedAt.formatDateTime()}", color = Color.Gray)
            }
        }
    }
}