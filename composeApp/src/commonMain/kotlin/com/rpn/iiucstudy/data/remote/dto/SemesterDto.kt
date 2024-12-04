package com.rpn.iiucstudy.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SemesterDto(
    val title: String,
    val description: String = "",
    val color: String,
)