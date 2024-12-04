package com.rpn.iiucstudy.presentation.ui.home.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rpn.iiucstudy.domain.model.NavigationHistory
import com.rpn.iiucstudy.presentation.navigation.HomeScreenRoutes
import com.rpn.iiucstudy.presentation.navigation.Routes
import com.rpn.iiucstudy.presentation.ui.home.HomeScreenEvents
import com.rpn.iiucstudy.presentation.ui.home.HomeScreenState
import com.rpn.iiucstudy.presentation.ui.home.screen.CourseScreen
import com.rpn.iiucstudy.presentation.ui.home.screen.DepartmentScreen
import com.rpn.iiucstudy.presentation.ui.home.screen.SemesterScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreensNavigation(
    rootNavController: NavController,
    homeNavController: NavHostController,
    state: HomeScreenState,
    onEvent: (HomeScreenEvents) -> Unit
) {
    NavHost(
        navController = homeNavController,
        startDestination = HomeScreenRoutes.Department.route/*from state. setting.*/
    ) {
        composable(route = HomeScreenRoutes.Department.route) {
            DepartmentScreen(
                state = state,
                onEvent = onEvent,
                onDepartmentClick = { department ->
                    onEvent(
                        HomeScreenEvents.AddToBackStack(
                            NavigationHistory(
                                directory = HomeScreenRoutes.Department.route,
                                value = department
                            )
                        )
                    )
                    onEvent(HomeScreenEvents.LoadSemesterData)
                    homeNavController.navigate(HomeScreenRoutes.Semester.route)
                }
            )
        }
        composable(route = HomeScreenRoutes.Semester.route) {
            SemesterScreen(
                state = state,
                onEvent = onEvent,
                onSemesterClick = { semester ->
                    onEvent(
                        HomeScreenEvents.AddToBackStack(
                            NavigationHistory(
                                directory = HomeScreenRoutes.Semester.route,
                                value = semester
                            )
                        )
                    )
                    homeNavController.navigate(HomeScreenRoutes.Course.route)
                }
            )
        }
        composable(route = HomeScreenRoutes.Course.route) {

            CourseScreen(
                state = state,
                onEvent = onEvent,
                onCourseClick = { course ->

                    val courseString = Json.encodeToString(course)
                    rootNavController.currentBackStackEntry?.savedStateHandle?.apply {
                        set("course", courseString)
                    }
                    rootNavController.navigate(Routes.CourseDetails.route)
                }
            )
        }
    }

}