package com.rpn.iiucstudy.presentation.ui.setting

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.rpn.iiucstudy.presentation.utils.Theme
import com.rpn.iiucstudy.presentation.ui.setting.component.BookmarkDialog
import com.rpn.iiucstudy.presentation.ui.setting.component.SettingItem
import com.rpn.iiucstudy.presentation.ui.setting.component.ThemeSelectionDialog
import iiuc_study.composeapp.generated.resources.Res
import iiuc_study.composeapp.generated.resources.delete_bookmark
import iiuc_study.composeapp.generated.resources.go_back
import iiuc_study.composeapp.generated.resources.ic_delete
import iiuc_study.composeapp.generated.resources.ic_light_mode
import iiuc_study.composeapp.generated.resources.setting
import iiuc_study.composeapp.generated.resources.theme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController, settingViewModel: SettingViewModel) {

    var showThemeSelectionDialog by remember { mutableStateOf(false) }
    val currentTheme by settingViewModel.currentTheme.collectAsState()
    var showDeleteBookmarkArticleDialog by remember { mutableStateOf(false) }

    when {
        showDeleteBookmarkArticleDialog -> {
            BookmarkDialog(
                onDeleteHistory = {
                    settingViewModel.deleteHistory()
                    showDeleteBookmarkArticleDialog = false
                },
                onDismissRequest = {
                    showDeleteBookmarkArticleDialog = false
                }
            )

        }

        showThemeSelectionDialog -> {
            ThemeSelectionDialog(
                onThemeChange = { theme ->
                    settingViewModel.changeThemeMode(theme.name)
                    showThemeSelectionDialog = false
                },
                onDismissRequest = { showThemeSelectionDialog = false },
                currentTheme = currentTheme ?: Theme.DARK_MODE.name
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.setting)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.go_back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                SettingItem(
                    onClick = {
                        showThemeSelectionDialog = true
                    },
                    painter = painterResource(Res.drawable.ic_light_mode),
                    itemName = stringResource(Res.string.theme)
                )
            }
            item {
                SettingItem(
                    onClick = {
                        showDeleteBookmarkArticleDialog = true
                    },
                    painter = painterResource(Res.drawable.ic_delete),
                    itemName = stringResource(Res.string.delete_bookmark),
                    itemColor = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
