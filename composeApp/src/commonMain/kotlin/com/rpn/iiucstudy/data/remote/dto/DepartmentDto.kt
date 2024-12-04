package com.rpn.iiucstudy.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class DepartmentDto(
    val chairman: String,
    val department: String,
    val department_name: String,
    val description: String,
    val event_html: String,
    val group_link: String,
    val image: String,
    val color: String,
    val last_update_time: String
)