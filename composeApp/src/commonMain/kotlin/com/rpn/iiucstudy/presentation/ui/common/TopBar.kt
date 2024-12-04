package com.rpn.iiucstudy.presentation.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.rpn.iiucstudy.domain.model.NavigationHistory
import com.rpn.iiucstudy.getType
import com.rpn.iiucstudy.presentation.utils.Type
import com.rpn.iiucstudy.presentation.utils.capitalizeFirstLetter
import iiuc_study.composeapp.generated.resources.Res
import iiuc_study.composeapp.generated.resources.setting
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    enableBackButton: Boolean = false,
    onSettingClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
        ),
        title = {
            Text(
                text = title.capitalizeFirstLetter(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if (enableBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Localized description"
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = onSettingClick) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = stringResource(Res.string.setting)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHome(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    navigationHistoryList: List<NavigationHistory>,
    enableBackButton: Boolean = false,
    onSettingClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    val isDesktop = remember { getType() == Type.Desktop }
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
        ),
        title = {
            Column {

                Text(
                    text = title.capitalizeFirstLetter(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                AnimatedVisibility(navigationHistoryList.isNotEmpty()) {
                    Row(modifier = Modifier) {
                        navigationHistoryList.forEachIndexed { index, navigationHistory ->
                            Text(
                                text = navigationHistory.value,
                                modifier = Modifier.clickable {
                                    // Navigate directly to the screen when clicked
                                    /*screensNavController.navigate(currentScreen.route) {
                                        popUpTo(HomeScreenRoutes.Department.route) { inclusive = true }
                                    }*/
                                },
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    textDecoration = TextDecoration.Underline
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            if (index < navigationHistoryList.lastIndex) {
                                Text(" -> ", style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                    }
                }
            }
        },
        navigationIcon = {

            AnimatedVisibility(enableBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Localized description"
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = onSettingClick) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = stringResource(Res.string.setting)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )

}