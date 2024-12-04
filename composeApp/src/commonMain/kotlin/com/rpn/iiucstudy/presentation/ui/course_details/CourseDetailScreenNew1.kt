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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.rpn.iiucstudy.domain.model.Course

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailScreenx(
    state: CourseDetailScreenState,
    onEvent: (CourseDetailScreenEvents) -> Unit,
    currentCourse: Course,
    onBackClick: () -> Unit
) {
//    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
    Column(modifier = Modifier.fillMaxSize()) {

        // Course Header Section
        CourseHeaderSection()

        // Course Video Section
        CourseVideoSectionX()

        // Discover Section (Optional)
        DiscoverSection()

        // Instructor Section
        InstructorSection()
    }
}

@Composable
fun CourseHeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Introduction to Programming",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "This course covers the basics of programming, including variables, control structures, and functions.",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Info about sessions, duration, and Learn More button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "12 sessions · 1 hour")
            Button(onClick = { /* Handle Learn More */ }) {
                Text(text = "Learn More")
            }
        }
    }
}

@Composable
fun CourseVideoSectionX() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Course Video",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item { // Header item
                Text(text = "Header", style = MaterialTheme.typography.titleLarge)
            }

            items(getVideoList()) { video ->
                VideoItem(video)
            }

            item { // Footer item or other sections
                Text(text = "Footer", style = MaterialTheme.typography.titleMedium)
            }
        }

    }
}

@Composable
fun VideoItem(video: Video) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            // Video title and duration
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = video.title,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(text = "${video.duration} mins")
            }
        }
    }
}

@Composable
fun DiscoverSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Discover New",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        LazyRow {
            items(getDiscoverItems()) { item ->
                DiscoverItem(item)
            }
        }
    }
}

@Composable
fun DiscoverItem(item: DiscoverItem) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .padding(8.dp),
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(model = item.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun InstructorSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Instructors",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow {
            items(getInstructors()) { instructor ->
                InstructorItem(instructor)
            }
        }
    }
}

@Composable
fun InstructorItem(instructor: Instructor) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = instructor.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = instructor.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = instructor.role,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

/*
val image1 =
    "https://images.unsplash.com/photo-1576158113928-4c240eaaf360?q=80&w=1780&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
val image2 =
    "https://images.unsplash.com/photo-1576158114254-3ba81558b87d?q=80&w=1780&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
val image3 =
    "https://plus.unsplash.com/premium_photo-1697945800806-e5d8fe424928?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
val image4 =
    "https://images.unsplash.com/photo-1579159278991-f698b0667a16?q=80&w=1780&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
*/

// Mock data functions for demonstration
fun getVideoList(): List<Video> = listOf(
    Video("Introduction to Python", 10),
    Video("Advanced Java Programming", 45),
    Video("Web Development Basics", 30)
)

fun getDiscoverItems(): List<DiscoverItem> = listOf(
    DiscoverItem(image1, "Discover New Adventure"),
    DiscoverItem(image2, "Adventure Outdoors"),
    DiscoverItem(image3, "Unforgettable Memories")
)

fun getInstructors(): List<Instructor> = listOf(
    Instructor("Dr. John Smith", "Professor of Mathematics", image1),
    Instructor("Ms. Emily Davis", "Senior Software Developer", image2)
)

data class Video(val title: String, val duration: Int)
data class DiscoverItem(val imageUrl: String, val title: String)
data class Instructor(val name: String, val role: String, val imageUrl: String)



