package com.rpn.iiucstudy.presentation.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rpn.iiucstudy.domain.model.Course

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import kotlin.math.max
@Composable
fun CourseList(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
    courseList: List<Course>,
    onCourseClick: (Course) -> Unit
) {
    CourseGridList(
        modifier = modifier,
        isDarkTheme = isDarkTheme,
        courseList = courseList,
        onCourseClick = onCourseClick
    )
}

@Composable
fun CourseGridList(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
    courseList: List<Course>,
    onCourseClick: (Course) -> Unit
) {
    val courses = remember(courseList) { courseList.toList() }
    val listState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {

        LazyVerticalGrid(
            modifier = modifier.fillMaxSize(),
            columns = GridCells.Adaptive(200.dp),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            state = listState,
            reverseLayout = false
        ) {

            items(courses) { course ->
                CourseItem(
                    course = course,
                    isDarkTheme = isDarkTheme,
                    onClick = onCourseClick,
                    onLongClick = {}
                )
            }
        }

        // FAB to scroll to the top
        /*if (listState.firstVisibleItemIndex > 0) {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Scroll to top"
                )
            }
        }*/
    }
}

@Composable
fun CourseEqualHeightGridList(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
    courseList: List<Course>,
    onCourseClick: (Course) -> Unit
) {
    val courses = remember(courseList) { courseList.toList() }
    val listState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    // State to track maximum height for each row
    var rowHeights by remember { mutableStateOf<Map<Int, Dp>>(emptyMap()) }

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {

        LazyVerticalGrid(
            modifier = modifier.fillMaxSize(),
            columns = GridCells.Adaptive(160.dp),  // Adaptive column layout
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            state = listState,
            reverseLayout = false
        ) {

            itemsIndexed(courses) { index, course ->
                // Calculate row index
                val columnCount = listState.layoutInfo.visibleItemsInfo.size
                val rowIndex = index / (columnCount.takeIf { it > 0 } ?: 1)

                BoxWithConstraints(
                    modifier = Modifier.fillMaxWidth(1f / columnCount.coerceAtLeast(1))
                ) {
                    // Get the maximum height for this row
                    val maxHeight = rowHeights[rowIndex] ?: 0.dp

                    // Track and update the maximum height for the row
                    val currentHeight = this@BoxWithConstraints.maxHeight
                    LaunchedEffect(currentHeight) {
                        rowHeights = rowHeights.toMutableMap().apply {
                            put(rowIndex, max(currentHeight, maxHeight))
                        }
                    }

                    // Set the height of the item to the maximum height for the row
                        // CourseItem is your existing item composable
                        CourseItem(
                            course = course,
                            modifier = Modifier
                                .height(maxHeight),
                            isDarkTheme = isDarkTheme,
                            onClick = onCourseClick,
                            onLongClick = {}
                        )

                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CourseFlowList(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
    courseList: List<Course>,
    onCourseClick: (Course) -> Unit
) {
    val courses = remember(courseList) { courseList.toList() }
    val listState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        FlowRow (
            Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            maxItemsInEachRow = 5,
        ) {
            repeat(courses.size) { idx ->
                CourseItem(
                    course = courses[idx],
                    modifier = Modifier.fillMaxRowHeight(),
                    isDarkTheme = isDarkTheme,
                    onClick = onCourseClick,
                    onLongClick = {}
                )
            }
        }
    }
}