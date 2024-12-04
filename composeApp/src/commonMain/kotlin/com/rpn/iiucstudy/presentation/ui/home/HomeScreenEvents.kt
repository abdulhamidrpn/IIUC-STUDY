package com.rpn.iiucstudy.presentation.ui.home

import com.rpn.iiucstudy.domain.model.NavigationHistory

sealed class HomeScreenEvents {
    data object LoadDepartmentData : HomeScreenEvents()
    data object LoadSemesterData : HomeScreenEvents()
    data class LoadCourseData(val department: String, val semester: String) : HomeScreenEvents()
    data class AddToBackStack(val navigationHistory: NavigationHistory) : HomeScreenEvents()
    data class RemoveBackStack(val directory: String?) : HomeScreenEvents()
    data class UpdateHomeTitle(val directory: String) : HomeScreenEvents()
    data object PullToRefresh : HomeScreenEvents()
}

