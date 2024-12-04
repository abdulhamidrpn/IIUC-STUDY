package com.rpn.iiucstudy.presentation.navigation.graphs

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rpn.iiucstudy.di.koinViewModel
import com.rpn.iiucstudy.domain.model.Course
import com.rpn.iiucstudy.presentation.navigation.Graph
import com.rpn.iiucstudy.presentation.navigation.Routes
import com.rpn.iiucstudy.presentation.ui.MainScreen
import com.rpn.iiucstudy.presentation.ui.course_details.CourseDetailScreen
import com.rpn.iiucstudy.presentation.ui.course_details.CourseDetailViewModel
import com.rpn.iiucstudy.presentation.ui.home.HomeViewModel
import com.rpn.iiucstudy.presentation.ui.setting.SettingScreen
import com.rpn.iiucstudy.presentation.ui.setting.SettingViewModel
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RootNavGraph(settingViewModel: SettingViewModel) {
    val rootNavController = rememberNavController()
    val mainNavController = rememberNavController()
    val homeNavController = rememberNavController()
    SharedTransitionLayout {
        val sharedTransitionScope = this
        NavHost(
            navController = rootNavController,
            route = Graph.rootScreenGraph,
            startDestination = Graph.mainScreenGraph,
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            composable(route = Graph.mainScreenGraph) {

                MainScreen(
                    settingViewModel = settingViewModel,
                    animatedVisibilityScope = this,
                    sharedTransactionScope = sharedTransitionScope,
                    rootNavController = rootNavController,
                    mainNavController = mainNavController,
                    homeNavController = homeNavController
                )
            }

            composable(route = Routes.Setting.route) {
                SettingScreen(
                    navController = rootNavController,
                    settingViewModel = settingViewModel
                )
            }

            composable(route = Routes.CourseDetails.route) {
                rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("course")
                    ?.let { course ->
                        val headlineViewModel = koinViewModel<CourseDetailViewModel>()
                        val state by headlineViewModel.state.collectAsState()
                        val currentCourse: Course = Json.decodeFromString(course)
                        CourseDetailScreen(
                            state = state,
                            onEvent = headlineViewModel::onEvent,
                            currentCourse = currentCourse,
                            onBackClick = {
                                rootNavController.navigateUp()
                            }
                        )
                    }
            }
        }
    }
}