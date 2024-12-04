package com.rpn.iiucstudy.domain.use_case


import com.rpn.iiucstudy.data.utils.DataState
import com.rpn.iiucstudy.domain.model.CourseVideo
import com.rpn.iiucstudy.domain.repository.CourseDetailRepository
import kotlinx.coroutines.flow.Flow

class GetCourseVideoData(
    private val repository: CourseDetailRepository
) {
    suspend operator fun invoke(playlistId: String): Flow<DataState<List<CourseVideo>>> {
        return repository.getCourseVideos(playlistId)
    }
}