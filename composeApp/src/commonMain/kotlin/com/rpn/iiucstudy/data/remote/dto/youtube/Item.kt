package com.rpn.iiucstudy.data.remote.dto.youtube


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    @SerialName("id")
    val id: String,
    @SerialName("snippet")
    val snippet: Snippet
)