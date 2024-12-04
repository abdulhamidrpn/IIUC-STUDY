package com.rpn.iiucstudy.presentation.ui.home

import com.rpn.iiucstudy.domain.model.Course
import com.rpn.iiucstudy.domain.model.Department
import com.rpn.iiucstudy.domain.model.NavigationHistory
import com.rpn.iiucstudy.domain.model.Semester
import com.rpn.iiucstudy.presentation.navigation.HomeScreenRoutes
import com.rpn.iiucstudy.presentation.utils.Theme
import com.rpn.iiucstudy.utils.Resource

data class HomeScreenState(
    val departmentList: Resource<List<Department>> = Resource.Loading,
    val semesterList: Resource<List<Semester>> = Resource.Idle,
    val courseList: Resource<List<Course>> = Resource.Idle,
    val currentRoute: HomeScreenRoutes = HomeScreenRoutes.Department,
    val navigationHistory: List<NavigationHistory> = emptyList(),
    val pullRefresh: Boolean = false,
    val theme: String = Theme.SYSTEM_DEFAULT.name
)

