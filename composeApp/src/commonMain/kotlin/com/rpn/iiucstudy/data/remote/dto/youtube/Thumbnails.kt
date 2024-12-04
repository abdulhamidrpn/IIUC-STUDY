package com.rpn.iiucstudy.data.remote.dto.youtube


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Thumbnails(
    @SerialName("default")
    val default: Default,
    @SerialName("medium")
    val medium: Default,
    @SerialName("standard")
    val standard: Default
)

@Serializable
data class Default(
    @SerialName("height")
    val height: Int,
    @SerialName("url")
    val url: String,
    @SerialName("width")
    val width: Int
)