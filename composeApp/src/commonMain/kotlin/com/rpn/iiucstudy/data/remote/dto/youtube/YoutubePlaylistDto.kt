package com.rpn.iiucstudy.data.remote.dto.youtube


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YoutubePlaylistDto(
    @SerialName("items")
    val items: List<Item>,
    @SerialName("pageInfo")
    val pageInfo: PageInfo
)