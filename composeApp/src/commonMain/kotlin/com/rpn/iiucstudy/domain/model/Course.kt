package com.rpn.iiucstudy.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Course(
    val courseLink: String,
    val courseType: String,
    val creditHours: Double,
    val description: String,
    val instructors: String,
    val lastUpdateTime: String,
    val prerequisites: String,
    val questions: String,
    val resourceLink: String,
    val semester: String,
    val subjectCode: String,
    val syllabus: String,
    val title: String
)