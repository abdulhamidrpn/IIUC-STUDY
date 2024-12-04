package com.rpn.iiucstudy.domain.repository

import com.rpn.iiucstudy.data.utils.DataState
import com.rpn.iiucstudy.domain.model.Course
import com.rpn.iiucstudy.domain.model.CourseVideo
import com.rpn.iiucstudy.domain.model.Department
import kotlinx.coroutines.flow.Flow

interface CourseDetailRepository {
    suspend fun getCourseVideos(playlistId: String): Flow<DataState<List<CourseVideo>>>
}