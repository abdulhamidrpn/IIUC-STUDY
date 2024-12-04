package com.rpn.iiucstudy.data.remote.dto.youtube


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResourceId(
    @SerialName("videoId")
    val videoId: String
)