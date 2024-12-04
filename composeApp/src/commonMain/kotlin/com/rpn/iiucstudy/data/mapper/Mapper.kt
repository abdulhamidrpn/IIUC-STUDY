package com.rpn.iiucstudy.data.mapper

import com.rpn.iiucstudy.data.remote.dto.CourseDto
import com.rpn.iiucstudy.data.remote.dto.DepartmentDto
import com.rpn.iiucstudy.data.remote.dto.SemesterDto
import com.rpn.iiucstudy.data.remote.dto.youtube.Item
import com.rpn.iiucstudy.data.remote.dto.youtube.YoutubePlaylistDto
import com.rpn.iiucstudy.domain.model.Course
import com.rpn.iiucstudy.domain.model.CourseVideo
import com.rpn.iiucstudy.domain.model.Department
import com.rpn.iiucstudy.domain.model.Semester


fun DepartmentDto.toDomainModel(): Department {
    return Department(
        chairman = this.chairman,
        department = this.department,
        departmentName = this.department_name,
        description = this.description,
        eventHtml = this.event_html,
        groupLink = this.group_link,
        image = this.image,
        color = this.color,
        lastUpdateTime = this.last_update_time
    )
}

fun List<DepartmentDto>.toDomainDepartmentModelList(): List<Department> {
    return this.map { it.toDomainModel() }
}

fun SemesterDto.toDomainModel(): Semester {
    return Semester(
        title = this.title,
        description = this.description,
        color = this.color,
    )
}

fun List<SemesterDto>.toDomainSemesterModelList(): List<Semester> {
    return this.map { it.toDomainModel() }
}

fun CourseDto.toDomainModel(): Course {
    return Course(
        courseLink = this.courseLink,
        courseType = this.courseType,
        creditHours = this.creditHours,
        description = this.description,
        instructors = this.instructors,
        lastUpdateTime = this.lastUpdateTime,
        prerequisites = this.prerequisites,
        questions = this.questions,
        resourceLink = this.resourceLink,
        semester = this.semester,
        subjectCode = this.subjectCode,
        syllabus = this.syllabus,
        title = this.title
    )
}

fun List<CourseDto>.toDomainCourseModelList(): List<Course> {
    return this.map { it.toDomainModel() }
}




fun Item.toDomainModel(): CourseVideo {
    return CourseVideo(
        channelId = this.snippet.channelId,
        channelTitle = this.snippet.channelTitle,
        playlistId = this.snippet.playlistId,

        publishedAt = this.snippet.publishedAt,
        thumbnailsStandard = this.snippet.thumbnails.standard.url,
        thumbnailsDefault = this.snippet.thumbnails.default.url,

        videoTitle = this.snippet.title,
        videoDescription = this.snippet.description,
        videoId = this.snippet.resourceId.videoId,
        videoOwnerChannelId = this.snippet.videoOwnerChannelId,
        videoOwnerChannelTitle = this.snippet.videoOwnerChannelTitle
    )
}

fun List<Item>.toDomainCourseVideoModelList(): List<CourseVideo> {
    return this.map { it.toDomainModel() }
}