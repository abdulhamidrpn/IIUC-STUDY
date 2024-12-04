package com.rpn.iiucstudy.presentation.ui.history

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import com.rpn.iiucstudy.presentation.navigation.graphs.MainNavGraph
import com.rpn.iiucstudy.presentation.navigation.rails.items.navigationItems
import com.rpn.iiucstudy.presentation.navigation.rails.navbar.NavigationBottomBar
import com.rpn.iiucstudy.presentation.ui.common.EmptyContent
import com.rpn.iiucstudy.presentation.ui.common.ShimmerEffect
import com.rpn.iiucstudy.presentation.ui.common.TopBar
import iiuc_study.composeapp.generated.resources.Res
import iiuc_study.composeapp.generated.resources.ic_browse
import iiuc_study.composeapp.generated.resources.ic_empty_beach
import iiuc_study.composeapp.generated.resources.ic_network_error
import iiuc_study.composeapp.generated.resources.no_news
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    navController: NavController,
    state: HistoryScreenState,
    onEvent: (HistoryScreenEvents) -> Unit,
    onSettingClick: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults
        .enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                title = "History",
                scrollBehavior = scrollBehavior,
                onSettingClick = onSettingClick,
                onBackClick = {}
            )
        }
    ) {
        state.articles.DisplayResult(
            onIdle = {
                EmptyContent(
                    message = stringResource(Res.string.no_news),
                    icon = Res.drawable.ic_empty_beach,
                    onRetryClick = null
                )
            },
            onLoading = {
                ShimmerEffect()
            },
            onSuccess = { articles ->
                if (articles.isEmpty()) EmptyContent(
                    message = stringResource(Res.string.no_news),
                    icon = Res.drawable.ic_browse,
                    onRetryClick = {
                        onEvent(HistoryScreenEvents.LoadHomeData)
                    }
                ) else {
                    /*Show List in different compose function*/
                }
            },
            onError = {
                EmptyContent(
                    message = it,
                    icon = Res.drawable.ic_network_error,
                    onRetryClick = {
                        onEvent(HistoryScreenEvents.LoadHomeData)
                    }
                )
            }
        )
    }
}