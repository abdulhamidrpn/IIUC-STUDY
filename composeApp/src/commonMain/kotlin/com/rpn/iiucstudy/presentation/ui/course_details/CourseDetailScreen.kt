package com.rpn.iiucstudy.presentation.ui.course_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.rpn.iiucstudy.domain.model.Course

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailScreen(
    state: CourseDetailScreenState,
    onEvent: (CourseDetailScreenEvents) -> Unit,
    currentCourse: Course,
    onBackClick: () -> Unit
) {
//    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Header
        HeaderSection()

        // Featured Courses Section
        Spacer(modifier = Modifier.height(16.dp))
        FeaturedCoursesSection()

        // Course Video Section
        Spacer(modifier = Modifier.height(16.dp))
        CourseVideoSection()

        // Course Video Section
        Spacer(modifier = Modifier.height(16.dp))
        MoreSection()

    }
}



@Composable
fun HeaderSection() {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Introduction to Programming",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "This course covers the basics of programming, including variables, control structures, and functions.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "12 Sessions",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "1 Hour",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            Button(
                onClick = {
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Learn More")
            }
        }
    }
}

@Composable
fun CourseVideoSection() {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Course Video",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            val courseVideos = listOf(
                CourseVideoItem(
                    title = "Introduction to Python",
                    duration = "10:00",
                    thumbnails = image1,
                    isCompleted = true
                ),
                CourseVideoItem(
                    title = "Advanced Java Programming",
                    duration = "45:00",
                    thumbnails = image2,
                    isCompleted = true
                ),
                CourseVideoItem(
                    title = "Web Development Basics",
                    duration = "30:00",
                    thumbnails = image3,
                    isCompleted = false
                ),
                CourseVideoItem(
                    title = "Web Development Basics",
                    duration = "30:00",
                    thumbnails = image4,
                    isCompleted = false
                )
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(courseVideos) { courseVideo ->
                    CourseVideoRow(courseVideo)
                }
            }
        }
    }
}

data class CourseVideoItem(
    val title: String,
    val duration: String,
    val thumbnails: String,
    val isCompleted: Boolean
)

@Composable
fun CourseVideoRow(courseVideo: CourseVideoItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (courseVideo.isCompleted) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Filled.CheckCircle,
                contentDescription = "Completed",
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = courseVideo.title,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Video - ${courseVideo.duration}",
                style = MaterialTheme.typography.labelSmall
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = rememberAsyncImagePainter(model = courseVideo.thumbnails),
            contentDescription = "Course Icon",
            modifier = Modifier.size(48.dp)
        )
    }
}

@Composable
fun FeaturedCoursesSection() {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Featured Courses",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            val courses = listOf(
                CourseItem(
                    title = "Basic Mathematics",
                    instructor = "Dr. John Smith",
                    instructorRole = "Professor of Mathematics",
                    duration = "10 hours",
                    lessons = "20 lessons",
                    image = image1
                ),
                CourseItem(
                    title = "Basic Mathematics",
                    instructor = "Dr. John Smith",
                    instructorRole = "Professor of Mathematics",
                    duration = "10 hours",
                    lessons = "20 lessons",
                    image = image2
                ),
                CourseItem(
                    title = "Basic Mathematics",
                    instructor = "Dr. John Smith",
                    instructorRole = "Professor of Mathematics",
                    duration = "10 hours",
                    lessons = "20 lessons",
                    image = image3
                ),
                CourseItem(
                    title = "Introduction to Programming",
                    instructor = "Ms. Emily Davis",
                    instructorRole = "Senior Software Developer",
                    duration = "15 hours",
                    lessons = "30 lessons",
                    image = image4
                ),
                CourseItem(
                    title = "Introduction to Programming",
                    instructor = "Ms. Emily Davis",
                    instructorRole = "Senior Software Developer",
                    duration = "15 hours",
                    lessons = "30 lessons",
                    image = image1
                )
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            ) {
                items(courses) { course ->
                    CourseRow(course)
                }
            }
        }
    }
}

data class CourseItem(
    val title: String,
    val instructor: String,
    val instructorRole: String,
    val duration: String,
    val lessons: String,
    val image: String
)

@Composable
fun CourseRow(course: CourseItem) {
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(course.image),
                contentDescription = "Course Image",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = course.title,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "${course.instructor} - ${course.instructorRole}",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "${course.duration} hours - ${course.lessons} lessons",
                    style = MaterialTheme.typography.labelSmall
                )
            }
            IconButton(onClick = {
            }) {
                Icon(
                    imageVector = androidx.compose.material.icons.Icons.Filled.Bookmark,
                    contentDescription = "Bookmark"
                )
            }
        }
    }
}

@Composable
fun MoreSection() {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "More",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            val moreItems = listOf(
                MoreItem(
                    title = "Discover New...",
                    description = "Explore the world...",
                    image = image1
                ),
                MoreItem(
                    title = "Adventure...",
                    description = "Join us for thrilling...",
                    image = image2
                ),
                MoreItem(
                    title = "Unforgettable...",
                    description = "Create memories...",
                    image = image3
                )
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            ) {
                items(moreItems) { moreItem ->
                    MoreRow(moreItem)
                }
            }
        }
    }
}

data class MoreItem(
    val title: String,
    val description: String,
    val image: String
)

@Composable
fun MoreRow(moreItem: MoreItem) {
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = moreItem.image),
                contentDescription = "More Item Image",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = moreItem.title,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = moreItem.description,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}


val image1 =
    "https://images.unsplash.com/photo-1576158113928-4c240eaaf360?q=80&w=1780&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
val image2 =
    "https://images.unsplash.com/photo-1576158114254-3ba81558b87d?q=80&w=1780&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
val image3 =
    "https://plus.unsplash.com/premium_photo-1697945800806-e5d8fe424928?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
val image4 =
    "https://images.unsplash.com/photo-1579159278991-f698b0667a16?q=80&w=1780&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"


