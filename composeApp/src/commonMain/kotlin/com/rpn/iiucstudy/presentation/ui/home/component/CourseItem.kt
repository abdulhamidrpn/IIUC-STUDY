package com.rpn.iiucstudy.presentation.ui.home.component


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rpn.iiucstudy.domain.model.Course
import com.rpn.iiucstudy.domain.model.Semester
import com.rpn.iiucstudy.presentation.utils.generateRandomSaturatedColor


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CourseItemOriginal(
    course: Course,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
    onClick: (Course) -> Unit,
    onLongClick: () -> Unit,
) {

    val departmentColor = generateRandomSaturatedColor()// semester.color.toColor()
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .combinedClickable(
                onClick = { onClick(course) },
                onLongClick = onLongClick
            ),
        border = BorderStroke(2.dp, departmentColor),
        colors = CardDefaults.cardColors(
            containerColor = departmentColor
                .copy(alpha = if (isDarkTheme) 0.2f else 0.4f)
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Credit Hours: ${course.creditHours}",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelSmall,
                fontStyle = FontStyle.Italic,
                maxLines = 1,
                modifier = Modifier.padding(vertical = 2.dp).align(Alignment.End)
            )
            Text(
                text = course.courseType,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelSmall,
                fontStyle = FontStyle.Italic,
                maxLines = 1,
                modifier = Modifier.align(Alignment.End)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 6f)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = course.subjectCode,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            }


            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = course.title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 2.dp).align(Alignment.CenterHorizontally)
            )

        }
    }

}




@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CourseItem(
    course: Course,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
    onClick: (Course) -> Unit,
    onLongClick: () -> Unit,
) {
    val departmentColor = generateRandomSaturatedColor()

    // Define a constant height for the text area
    val textMinHeight = with(LocalDensity.current) { 40.dp.toPx() } // Adjust this value as needed

    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .combinedClickable(
                onClick = { onClick(course) },
                onLongClick = onLongClick
            ),
        border = BorderStroke(2.dp, departmentColor),
        colors = CardDefaults.cardColors(
            containerColor = departmentColor
                .copy(alpha = if (isDarkTheme) 0.2f else 0.4f)
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Credit Hours: ${course.creditHours}",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelSmall,
                fontStyle = FontStyle.Italic,
                maxLines = 1,
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .align(Alignment.End)
            )

            Text(
                text = course.courseType,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelSmall,
                fontStyle = FontStyle.Italic,
                maxLines = 1,
                modifier = Modifier.align(Alignment.End)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 6f)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = course.subjectCode,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Ensure the Text area at the bottom has a fixed height
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 48.dp) // This sets a minimum height for this text box
            ) {
                Text(
                    text = course.title,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(vertical = 2.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}
