package com.rpn.iiucstudy.data.remote.dto.youtube


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Snippet(
    @SerialName("channelId")
    val channelId: String,
    @SerialName("channelTitle")
    val channelTitle: String,
    @SerialName("description")
    val description: String,
    @SerialName("playlistId")
    val playlistId: String,
    @SerialName("position")
    val position: Int,
    @SerialName("publishedAt")
    val publishedAt: String,
    @SerialName("resourceId")
    val resourceId: ResourceId,
    @SerialName("thumbnails")
    val thumbnails: Thumbnails,
    @SerialName("title")
    val title: String,
    @SerialName("videoOwnerChannelId")
    val videoOwnerChannelId: String,
    @SerialName("videoOwnerChannelTitle")
    val videoOwnerChannelTitle: String
)