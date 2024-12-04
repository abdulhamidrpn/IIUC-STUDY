package com.rpn.iiucstudy.presentation.ui

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rpn.iiucstudy.presentation.navigation.graphs.MainNavGraph
import com.rpn.iiucstudy.presentation.navigation.rails.items.navigationItems
import com.rpn.iiucstudy.presentation.navigation.rails.navbar.NavigationBottomBar
import com.rpn.iiucstudy.presentation.navigation.rails.navbar.NavigationSideBar
import com.rpn.iiucstudy.presentation.theme.isNotCompactWindowSize
import com.rpn.iiucstudy.presentation.ui.setting.SettingViewModel

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class,
    ExperimentalSharedTransitionApi::class
)
@Composable
fun MainScreen(
    settingViewModel: SettingViewModel,
    rootNavController: NavHostController,
    mainNavController: NavHostController,
    homeNavController: NavHostController,
    sharedTransactionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()

    var previousRoute by rememberSaveable {
        mutableStateOf(navBackStackEntry?.destination?.route)
    }
    val currentRoute by remember(navBackStackEntry) {
        derivedStateOf { navBackStackEntry?.destination?.route }
    }

    DisposableEffect(Unit) {
        onDispose {
            previousRoute = currentRoute
        }
    }
    LaunchedEffect(Unit) {
        if (previousRoute != null) {
            mainNavController.navigate(previousRoute!!) {
                mainNavController.graph.startDestinationRoute?.let { startDestinationRoute ->
                    popUpTo(startDestinationRoute) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    }

    val useNavRail = isNotCompactWindowSize()

    val scrollBehavior = TopAppBarDefaults
        .enterAlwaysScrollBehavior(rememberTopAppBarState())

    if (useNavRail) {
        Row {
            NavigationSideBar(
                items = navigationItems,
                currentRoute = currentRoute,
                onItemClick = { currentBottomNavigationItem ->
                    mainNavController.navigate(currentBottomNavigationItem.route) {
                        mainNavController.graph.startDestinationRoute?.let { startDestinationRoute ->
                            popUpTo(startDestinationRoute) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )

            Scaffold(modifier = Modifier.weight(1f)) {
                MainNavGraph(
                    rootNavController = rootNavController,
                    mainNavController = mainNavController,
                    homeNavController = homeNavController,
                    paddingValues = it
                )
            }

        }
    } else {
        Scaffold(
            modifier = Modifier,
            bottomBar = {
                BottomAppBar {
                    NavigationBottomBar(
                        items = navigationItems,
                        currentRoute = currentRoute,
                        onItemClick = { currentBottomNavigationItem ->
                            mainNavController.navigate(currentBottomNavigationItem.route) {
                                mainNavController.graph.startDestinationRoute?.let { startDestinationRoute ->
                                    popUpTo(startDestinationRoute) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        ) {
            MainNavGraph(
                rootNavController = rootNavController,
                mainNavController = mainNavController,
                homeNavController = homeNavController,
                paddingValues = it
            )

        }
    }
}