package com.rpn.iiucstudy.presentation.ui.profile

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.rpn.iiucstudy.presentation.ui.common.EmptyContent
import com.rpn.iiucstudy.presentation.ui.common.ShimmerEffect
import iiuc_study.composeapp.generated.resources.Res
import iiuc_study.composeapp.generated.resources.ic_browse
import iiuc_study.composeapp.generated.resources.ic_empty_beach
import iiuc_study.composeapp.generated.resources.ic_network_error
import iiuc_study.composeapp.generated.resources.no_news
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    state: ProfileScreenState,
    onEvent: (ProfileScreenEvents) -> Unit
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
                    onEvent(ProfileScreenEvents.LoadHomeData)
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
                    onEvent(ProfileScreenEvents.LoadHomeData)
                }
            )
        }
    )
}