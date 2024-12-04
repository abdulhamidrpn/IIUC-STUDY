package com.rpn.iiucstudy.domain.model

import com.rpn.iiucstudy.getRandomId
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val author: String,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String?,
    val sourceName: String
){
    val id: String = getRandomId()
}
