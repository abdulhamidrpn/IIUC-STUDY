package com.rpn.iiucstudy.domain.repository

import com.rpn.iiucstudy.data.utils.DataState
import com.rpn.iiucstudy.domain.model.Course
import com.rpn.iiucstudy.domain.model.Department
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getDepartments(): Flow<DataState<List<Department>>>
    suspend fun getCourses(department:String,semester: String): Flow<DataState<List<Course>>>
}