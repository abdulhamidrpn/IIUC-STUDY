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
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.unit.dp
import com.rpn.iiucstudy.domain.model.Department
import com.rpn.iiucstudy.presentation.ui.common.MyImage
import com.rpn.iiucstudy.presentation.utils.formatDateTime
import com.rpn.iiucstudy.presentation.utils.generateRandomSaturatedColor


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DepartmentItem(
    department: Department,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    onLongClick: () -> Unit,
) {


    val departmentColor = generateRandomSaturatedColor()// department.color.toColor()
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(MaterialTheme.shapes.small)
            .combinedClickable(
                onClick = { onClick(department.department) },
                onLongClick = onLongClick
            ),
        border = BorderStroke(2.dp, departmentColor),
        colors = CardDefaults.cardColors(
            containerColor = departmentColor
                .copy(alpha = if (isDarkTheme) 0.2f else 0.8f)
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            MyImage(
                url = department.image,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(MaterialTheme.shapes.medium)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = department.department,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = department.departmentName,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic,
                maxLines = 2,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = department.description,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
            )

        }
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 4.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = "Last updated: " + department.lastUpdateTime.formatDateTime(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.align(Alignment.End).padding(horizontal = 8.dp, vertical = 8.dp)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DepartmentItemEqual(
    department: Department,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    onLongClick: () -> Unit,
) {
    val departmentColor = generateRandomSaturatedColor()

    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(MaterialTheme.shapes.small)
            .combinedClickable(
                onClick = { onClick(department.department) },
                onLongClick = onLongClick
            ),
        border = BorderStroke(2.dp, departmentColor),
        colors = CardDefaults.cardColors(
            containerColor = departmentColor
                .copy(alpha = if (isDarkTheme) 0.2f else 0.8f)
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            // Ensuring that the image always has a fixed aspect ratio
            MyImage(
                url = department.image,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f) // Consistent image ratio for uniformity
                    .clip(MaterialTheme.shapes.medium)
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Ensuring that the department title area has a minimum height
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 56.dp) // Adjust this height as necessary
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .align(Alignment.CenterStart),
                    text = department.department,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    style = MaterialTheme.typography.titleLarge,
                )
            }

            // Ensuring the department name has a consistent height
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 40.dp) // Adjust to ensure consistency across items
            ) {
                Text(
                    text = department.departmentName,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic,
                    maxLines = 2,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                        .align(Alignment.CenterStart)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Setting a minimum height for the description to ensure consistency
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 60.dp) // Adjust as necessary to accommodate multiple lines
            ) {
                Text(
                    text = department.description,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                        .align(Alignment.CenterStart)
                )
            }

        }
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 4.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.secondary
        )

        // Keeping the last updated text area consistent with a set height
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 24.dp) // Adjust as needed
        ) {
            Text(
                text = "Last updated: " + department.lastUpdateTime.formatDateTime(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            )
        }
    }
}
