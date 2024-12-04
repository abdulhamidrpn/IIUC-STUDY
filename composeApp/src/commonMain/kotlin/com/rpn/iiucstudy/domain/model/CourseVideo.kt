package com.rpn.iiucstudy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CourseVideo(
    val channelId: String,
    val channelTitle: String,
    val playlistId: String,
    val publishedAt: String,
    val thumbnailsStandard: String,
    val thumbnailsDefault: String,
    val videoTitle: String,
    val videoDescription: String,
    val videoId: String,
    val videoOwnerChannelId: String,
    val videoOwnerChannelTitle: String
)