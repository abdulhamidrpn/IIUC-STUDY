package com.rpn.iiucstudy.domain.model

import com.rpn.iiucstudy.getRandomId
import kotlinx.serialization.Serializable

@Serializable
data class Department(
    val chairman: String,
    val department: String,
    val departmentName: String,
    val description: String,
    val eventHtml: String,
    val groupLink: String,
    val image: String,
    val color:String,
    val lastUpdateTime: String
)