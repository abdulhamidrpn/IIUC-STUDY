package com.rpn.iiucstudy.domain.use_case


import com.rpn.iiucstudy.data.mapper.toDomainSemesterModelList
import com.rpn.iiucstudy.data.utils.Constants
import com.rpn.iiucstudy.data.utils.DataState
import com.rpn.iiucstudy.domain.model.Course
import com.rpn.iiucstudy.domain.model.Department
import com.rpn.iiucstudy.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetHomeData(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Flow<DataState<List<Department>>> {
        return repository.getDepartments()
    }
    suspend operator fun invoke(department:String,semester: String): Flow<DataState<List<Course>>> {
        return repository.getCourses(department,semester)
    }

    val semesterList = Constants.semesters.toDomainSemesterModelList()
}