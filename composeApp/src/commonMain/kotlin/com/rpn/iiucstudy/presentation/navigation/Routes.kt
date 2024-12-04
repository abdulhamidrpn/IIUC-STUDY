package com.rpn.iiucstudy.presentation.navigation


object Graph {
    const val rootScreenGraph = "root_screen_graph"
    const val mainScreenGraph = "main_screen_graph"
    const val homeScreenGraph = "home_screen_graph"
}

sealed class HomeScreenRoutes(val route: String) {
    data object Department : HomeScreenRoutes("department")
    data object Semester : HomeScreenRoutes("semester")
    data object Course : HomeScreenRoutes("course")
}

sealed class MainScreenRoutes(val route: String) {
    data object Home : MainScreenRoutes("home")
    data object History : MainScreenRoutes("history")
    data object Profile : MainScreenRoutes("profile")
}

sealed class Routes(val route: String) {
    data object Setting : Routes("setting")
    data object CourseDetails : Routes("courseDetails")
}

