package com.rpn.iiucstudy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NavigationHistory(
    val directory: String = "",
    val value: String = "",
)

fun List<NavigationHistory>.getValueForDirectory(directoryToMatch: String): String {
    return this.find { it.directory == directoryToMatch }?.value ?:""
}
