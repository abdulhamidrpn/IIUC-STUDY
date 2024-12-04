package com.rpn.iiucstudy.presentation.ui.course_details

import com.rpn.iiucstudy.domain.model.CourseVideo

sealed class CourseDetailScreenEvents {
    data class SetCurrentVideo(val currentVideo:  CourseVideo) : CourseDetailScreenEvents()
    data object LoadCourseVideos : CourseDetailScreenEvents()
}

