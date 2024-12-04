package com.rpn.iiucstudy.presentation.navigation.rails.items

import com.rpn.iiucstudy.presentation.navigation.MainScreenRoutes
import iiuc_study.composeapp.generated.resources.Res
import iiuc_study.composeapp.generated.resources.*
import iiuc_study.composeapp.generated.resources.ic_history
import iiuc_study.composeapp.generated.resources.ic_home
import iiuc_study.composeapp.generated.resources.ic_user
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class NavigationItem(
    val label: StringResource,
    val route: String,
    val unselectedIcon: DrawableResource,
    val selectedIcon: DrawableResource,
    val hasNews: Boolean = false,
    val badgeCount: Int? = null,
)


val navigationItems = listOf(
    NavigationItem(
        label = Res.string.home,
        route = MainScreenRoutes.Home.route,
        selectedIcon = Res.drawable.ic_home,
        unselectedIcon = Res.drawable.ic_home,
    ),
    NavigationItem(
        label = Res.string.history,
        route = MainScreenRoutes.History.route,
        selectedIcon = Res.drawable.ic_align_top_center,
        unselectedIcon = Res.drawable.ic_align_top_center
    ),
    NavigationItem(
        label = Res.string.profile,
        route = MainScreenRoutes.Profile.route,
        selectedIcon = Res.drawable.ic_user,
        unselectedIcon = Res.drawable.ic_user
    )
)