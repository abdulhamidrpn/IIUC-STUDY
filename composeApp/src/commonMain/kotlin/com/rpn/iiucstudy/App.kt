package com.rpn.iiucstudy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.rpn.iiucstudy.di.koinViewModel
import com.rpn.iiucstudy.presentation.navigation.graphs.RootNavGraph
import com.rpn.iiucstudy.presentation.theme.AppTheme
import com.rpn.iiucstudy.presentation.ui.setting.SettingViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    KoinContext {
        val settingViewModel = koinViewModel<SettingViewModel>()
        val currentTheme by settingViewModel.currentTheme.collectAsState()
        AppTheme(currentTheme) {
            RootNavGraph(settingViewModel)
        }
    }
}