package com.rpn.iiucstudy.presentation.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rpn.iiucstudy.domain.model.Department
import com.rpn.iiucstudy.presentation.theme.getDimens

@Composable
fun DepartmentList(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
    departmentList: List<Department>,
    onDepartmentClick: (String) ->Unit
) {
    val departments = remember(departmentList) { departmentList.toList() }
    val listState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        LazyVerticalGrid(
            modifier = modifier.fillMaxSize(),
            columns = GridCells.Adaptive(getDimens().imageWidth),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            state = listState,
        ) {

            items(departments) { department ->
                DepartmentItem(
                    department = department,
                    isDarkTheme = isDarkTheme,
                    onClick = onDepartmentClick,
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