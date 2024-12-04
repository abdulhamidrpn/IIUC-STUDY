package com.rpn.iiucstudy.presentation.ui.home.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rpn.iiucstudy.presentation.theme.isSettingDarkTheme
import com.rpn.iiucstudy.presentation.ui.common.EmptyContent
import com.rpn.iiucstudy.presentation.ui.common.ShimmerEffect
import com.rpn.iiucstudy.presentation.ui.home.HomeScreenEvents
import com.rpn.iiucstudy.presentation.ui.home.HomeScreenState
import com.rpn.iiucstudy.presentation.ui.home.component.SemesterList
import iiuc_study.composeapp.generated.resources.Res
import iiuc_study.composeapp.generated.resources.ic_browse
import iiuc_study.composeapp.generated.resources.ic_empty_beach
import iiuc_study.composeapp.generated.resources.ic_network_error
import iiuc_study.composeapp.generated.resources.no_news
import org.jetbrains.compose.resources.stringResource


@Composable
fun SemesterScreen(
    modifier: Modifier = Modifier,
    state: HomeScreenState,
    onEvent: (HomeScreenEvents) -> Unit,
    onSemesterClick: (String) -> Unit,
) {


    state.semesterList.DisplayResult(
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
        onSuccess = { semesterList ->
            if (semesterList.isEmpty()) {
                EmptyContent(
                    message = stringResource(Res.string.no_news),
                    icon = Res.drawable.ic_browse,
                    onRetryClick = { onEvent(HomeScreenEvents.LoadDepartmentData) }
                )
            } else {
                SemesterList(
                    semesterList = semesterList,
                    isDarkTheme = isSettingDarkTheme(state.theme),
                    onSemesterClick = onSemesterClick
                )
            }
        },
        onError = {
            EmptyContent(
                message = it,
                icon = Res.drawable.ic_network_error,
                onRetryClick = {
                    onEvent(HomeScreenEvents.LoadDepartmentData)
                }
            )
        }
    )

}