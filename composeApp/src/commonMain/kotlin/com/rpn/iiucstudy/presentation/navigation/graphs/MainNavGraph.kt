package com.rpn.iiucstudy.presentation.navigation.graphs

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rpn.iiucstudy.di.koinViewModel
import com.rpn.iiucstudy.presentation.navigation.Graph
import com.rpn.iiucstudy.presentation.navigation.MainScreenRoutes
import com.rpn.iiucstudy.presentation.navigation.Routes
import com.rpn.iiucstudy.presentation.ui.history.HistoryScreen
import com.rpn.iiucstudy.presentation.ui.history.HistoryViewModel
import com.rpn.iiucstudy.presentation.ui.home.HomeScreen
import com.rpn.iiucstudy.presentation.ui.home.HomeViewModel
import com.rpn.iiucstudy.presentation.ui.profile.ProfileScreen
import com.rpn.iiucstudy.presentation.ui.profile.ProfileViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainNavGraph(
    rootNavController: NavHostController,
    mainNavController: NavHostController,
    homeNavController: NavHostController,
    paddingValues: PaddingValues,
) {


    NavHost(
        navController = mainNavController,
        route = Graph.mainScreenGraph,
        startDestination = MainScreenRoutes.Home.route,
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        composable(route = MainScreenRoutes.Home.route) {
            val headlineViewModel = koinViewModel<HomeViewModel>()
            val state by headlineViewModel.state.collectAsState()
            HomeScreen(
                rootNavController = rootNavController,
                homeNavController = homeNavController,
                state = state,
                onEvent = headlineViewModel::onEvent,
                onSettingClick = { rootNavController.navigate(Routes.Setting.route) }
            )
        }

        composable(route = MainScreenRoutes.History.route) {
            val historyViewModel = koinViewModel<HistoryViewModel>()
            val state by historyViewModel.state.collectAsState()
            HistoryScreen(
                navController = rootNavController,
                state = state,
                onEvent = historyViewModel::onEvent,
                onSettingClick = { rootNavController.navigate(Routes.Setting.route) }
            )
        }

        composable(route = MainScreenRoutes.Profile.route) {
            val profileViewModel = koinViewModel<ProfileViewModel>()
            val state by profileViewModel.state.collectAsState()
            ProfileScreen(
                navController = rootNavController,
                state = state,
                onEvent = profileViewModel::onEvent
            )
        }
    }
}