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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rpn.iiucstudy.domain.model.Semester
import com.rpn.iiucstudy.presentation.utils.generateRandomSaturatedColor


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SemesterItemOriginal(
    semester: Semester,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    onLongClick: () -> Unit,
) {

    val departmentColor = generateRandomSaturatedColor()// semester.color.toColor()
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(MaterialTheme.shapes.small)
            .combinedClickable(
                onClick = { onClick(semester.title) },
                onLongClick = onLongClick
            ),
        border = BorderStroke(2.dp, departmentColor),
        colors = CardDefaults.cardColors(
            containerColor = departmentColor
                .copy(alpha = if (isDarkTheme) 0.2f else 0.8f)
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = semester.title.split(" ").firstOrNull() ?: "",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        style = MaterialTheme.typography.titleLarge,
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = semester.title.split(" ").lastOrNull() ?: "",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium,
                        fontStyle = FontStyle.Italic,
                        maxLines = 2,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = semester.description,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 2.dp)
            )

        }
    }

}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SemesterItem(
    semester: Semester,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    onLongClick: () -> Unit,
) {

    val departmentColor = generateRandomSaturatedColor() // semester.color.toColor()

    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(MaterialTheme.shapes.small)
            .combinedClickable(
                onClick = { onClick(semester.title) },
                onLongClick = onLongClick
            ),
        border = BorderStroke(2.dp, departmentColor),
        colors = CardDefaults.cardColors(
            containerColor = departmentColor
                .copy(alpha = if (isDarkTheme) 0.2f else 0.8f)
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Ensuring the box containing the title has a consistent height
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = semester.title.split(" ").firstOrNull() ?: "",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        style = MaterialTheme.typography.titleLarge,
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = semester.title.split(" ").lastOrNull() ?: "",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium,
                        fontStyle = FontStyle.Italic,
                        maxLines = 2,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Setting a consistent height for the description text
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 60.dp) // Set to a minimum height for uniformity
            ) {
                Text(
                    text = semester.description,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(vertical = 2.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}
