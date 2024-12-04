package com.rpn.iiucstudy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Semester(
    val title: String,
    val description: String = "",
    val color: String,
)