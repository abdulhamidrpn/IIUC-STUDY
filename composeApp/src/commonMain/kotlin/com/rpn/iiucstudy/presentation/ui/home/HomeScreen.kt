package com.rpn.iiucstudy.presentation.ui.home

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rpn.iiucstudy.getType
import com.rpn.iiucstudy.presentation.navigation.HomeScreenRoutes
import com.rpn.iiucstudy.presentation.ui.common.TopBarHome
import com.rpn.iiucstudy.presentation.ui.home.navigation.HomeScreensNavigation
import com.rpn.iiucstudy.presentation.utils.Type

@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun HomeScreen(
    rootNavController: NavHostController,
    homeNavController: NavHostController,
    modifier: Modifier = Modifier,
    state: HomeScreenState,
    onEvent: (HomeScreenEvents) -> Unit,
    onSettingClick: () -> Unit
) {

    val isDesktop = remember { getType() == Type.Desktop }
    // State to track if the navigation is back
    var isBackNavigation by remember { mutableStateOf(false) }

    // Track the previous destination route
    var previousRoute by remember { mutableStateOf<String?>(null) }
    var currentRouteTitle by remember { mutableStateOf<String>("") }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBarHome(
                title = currentRouteTitle,
                scrollBehavior = scrollBehavior,
                enableBackButton = currentRouteTitle != HomeScreenRoutes.Department.route,
                navigationHistoryList = state.navigationHistory,
                onSettingClick = onSettingClick,
                onBackClick = {
                    homeNavController.navigateUp()
                }
            )
        }
    ) { paddingValues ->

        val pullRefreshState = rememberPullRefreshState(
            refreshing = state.pullRefresh,
            onRefresh = { onEvent(HomeScreenEvents.PullToRefresh) }
        )

        val pullRefreshModifier = if (!isDesktop)
            Modifier
                .pullRefresh(state = pullRefreshState)
                .padding(top = paddingValues.calculateTopPadding(), bottom = 100.dp)
        else Modifier
            .padding(top = paddingValues.calculateTopPadding())
        Box(
            modifier = modifier
                .fillMaxSize()
                .then(pullRefreshModifier),
            contentAlignment = Alignment.Center
        ) {

            HomeScreensNavigation(rootNavController,homeNavController, state, onEvent)

            PullRefreshIndicator(
                state.pullRefresh, pullRefreshState,
                Modifier.wrapContentSize().align(Alignment.TopCenter)
            )
        }
    }


    // Add a listener to the NavController to handle back navigation
    DisposableEffect(homeNavController) {
        val callback =
            NavController.OnDestinationChangedListener { controller, destination, arguments ->
                val currentRoute = destination.route

                // Determine if the navigation is back by comparing the current and previous routes
                isBackNavigation = previousRoute != null && previousRoute != currentRoute &&
                        homeNavController.previousBackStackEntry?.destination?.route != previousRoute

                // Update the previous route to the current one
                previousRoute = currentRoute
                currentRouteTitle = currentRoute ?: ""
                if (isBackNavigation) {
                    onEvent(HomeScreenEvents.RemoveBackStack(previousRoute ?: ""))
                }
                if (currentRoute == HomeScreenRoutes.Department.route) {
                    onEvent(HomeScreenEvents.RemoveBackStack(null))
                }
            }

        // Add the listener to the NavController
        homeNavController.addOnDestinationChangedListener(callback)

        // Remove the listener when the effect is disposed
        onDispose {
            homeNavController.removeOnDestinationChangedListener(callback)
            println("handleBackButton OnDispose")
        }
    }

}
