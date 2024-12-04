package com.rpn.iiucstudy.data.repository

import com.rpn.iiucstudy.data.mapper.toDomainCourseModelList
import com.rpn.iiucstudy.data.mapper.toDomainDepartmentModelList
import com.rpn.iiucstudy.data.remote.dto.CourseDto
import com.rpn.iiucstudy.data.remote.dto.DepartmentDto
import com.rpn.iiucstudy.data.utils.DataState
import com.rpn.iiucstudy.domain.model.Course
import com.rpn.iiucstudy.domain.model.Department
import com.rpn.iiucstudy.domain.repository.HomeRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
    private val client: HttpClient
) : HomeRepository {

    override suspend fun getDepartments(): Flow<DataState<List<Department>>> = flow {
        try {
            emit(DataState.Loading)

            val response = client.get {
                parameter("department", "HOME")
            }.body<List<DepartmentDto>>()

            val departments = response.toDomainDepartmentModelList()

            emit(DataState.Success(departments))
        } catch (e: Exception) {
            emit(DataState.Error(e.message ?: "Unknown error"))
        }
    }

    override suspend fun getCourses(department:String,semester: String): Flow<DataState<List<Course>>> = flow {
        try {
            emit(DataState.Loading)

            val response = client.get {
                parameter("department", department)
                parameter("semester", semester)
            }.body<List<CourseDto>>()

            val courses = response.toDomainCourseModelList()

            emit(DataState.Success(courses))
        } catch (e: Exception) {
            emit(DataState.Error(e.message ?: "Unknown error"))
        }
    }
}
