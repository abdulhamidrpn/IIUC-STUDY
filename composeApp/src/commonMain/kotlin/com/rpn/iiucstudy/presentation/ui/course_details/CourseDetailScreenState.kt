package com.rpn.iiucstudy.presentation.ui.course_details

import com.rpn.iiucstudy.domain.model.CourseVideo
import com.rpn.iiucstudy.utils.Resource

data class CourseDetailScreenState(
    val courseVideo: Resource<List<CourseVideo>> = Resource.Idle,
    val currentVideo: CourseVideo? = null,
    val homeTitle: String = ""
)

