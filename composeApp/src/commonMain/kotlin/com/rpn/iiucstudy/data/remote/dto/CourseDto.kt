package com.rpn.iiucstudy.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseDto(
    @SerialName("course_link")
    val courseLink: String,
    @SerialName("course_type")
    val courseType: String,
    @SerialName("credit_hours")
    val creditHours: Double,
    @SerialName("description")
    val description: String,
    @SerialName("instructors")
    val instructors: String,
    @SerialName("last_update_time")
    val lastUpdateTime: String,
    @SerialName("prerequisites")
    val prerequisites: String,
    @SerialName("questions")
    val questions: String,
    @SerialName("resource_link")
    val resourceLink: String,
    @SerialName("semester")
    val semester: String,
    @SerialName("subject_code")
    val subjectCode: String,
    @SerialName("syllabus")
    val syllabus: String,
    @SerialName("title")
    val title: String
)