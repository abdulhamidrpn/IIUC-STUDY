package com.rpn.iiucstudy.presentation.utils

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.ui.graphics.Color
import iiuc_study.composeapp.generated.resources.Res
import iiuc_study.composeapp.generated.resources.dark_mode
import iiuc_study.composeapp.generated.resources.light_mode
import iiuc_study.composeapp.generated.resources.system_default
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.StringResource
import kotlin.random.Random


enum class Theme(val title: StringResource) {
    SYSTEM_DEFAULT(Res.string.system_default),
    LIGHT_MODE(Res.string.light_mode),
    DARK_MODE(Res.string.dark_mode)
}

enum class Type {
    Mobile, Desktop, Browser
}

enum class WindowSizeClass { Compact, Medium, Expanded }

val FadeIn = fadeIn(animationSpec = tween(220, delayMillis = 90)) +
        scaleIn(
            initialScale = 0.92f,
            animationSpec = tween(220, delayMillis = 90)
        )

val FadeOut = fadeOut(animationSpec = tween(90))
fun String.capitalizeFirstLetter(): String {
    return this.replaceFirstChar { it.uppercase() }
}


fun String.toColor(): Color {
    return try {
        val colorCode = this.removePrefix("#")
        val colorInt = colorCode.toLong(16)
        Color(
            alpha = (colorInt shr 24 and 0xFF) / 255f,
            red = (colorInt shr 16 and 0xFF) / 255f,
            green = (colorInt shr 8 and 0xFF) / 255f,
            blue = (colorInt and 0xFF) / 255f
        )
    } catch (e: Exception) {
        generateRandomSaturatedColor()
    }
}

fun generateRandomSaturatedColor(): Color {
    val hue = Random.nextFloat() * 360f // Random hue value between 0 and 360
    val saturation = 0.8f // High saturation for vivid colors
    val lightness =
        Random.nextFloat() * 0.4f + 0.5f // Lightness between 50% and 90% to work well on both themes

    val desaturatedColor = hslToColor(hue, saturation, lightness)
    println("desaturatedColor ${desaturatedColor.colorSpace}")
    return hslToColor(hue, saturation, lightness)
}

fun hslToColor(hue: Float, saturation: Float, lightness: Float): Color {
    val c = (1 - kotlin.math.abs(2 * lightness - 1)) * saturation
    val x = c * (1 - kotlin.math.abs((hue / 60f) % 2 - 1))
    val m = lightness - c / 2

    val (r, g, b) = when {
        hue < 60f -> Triple(c, x, 0f)
        hue < 120f -> Triple(x, c, 0f)
        hue < 180f -> Triple(0f, c, x)
        hue < 240f -> Triple(0f, x, c)
        hue < 300f -> Triple(x, 0f, c)
        else -> Triple(c, 0f, x)
    }

    return Color((r + m), (g + m), (b + m))
}

fun LocalDateTime.format(pattern: String): String {
    return pattern
        .replace("yyyy", this.year.toString())
        .replace("MM", this.monthNumber.toString().padStart(2, '0'))
        .replace("dd", this.dayOfMonth.toString().padStart(2, '0'))
        .replace("HH", this.hour.toString().padStart(2, '0'))
        .replace("mm", this.minute.toString().padStart(2, '0'))
        .replace("ss", this.second.toString().padStart(2, '0'))
        .replace(
            "SSS",
            this.nanosecond.toString().padStart(9, '0').substring(0, 3)
        ) // for milliseconds
}

fun String.formatDateTime(): String {
    // Parse the input string to an Instant
    val instant = Instant.parse(this)

    // Convert the Instant to a LocalDateTime in UTC time zone
    val localDateTime = instant.toLocalDateTime(TimeZone.UTC)
    val outputPattern = "dd / MM / yyyy" //"yyyy-MM-dd HH:mm:ss.SSS"
    val formattedDate = localDateTime.format(outputPattern)
    return formattedDate
}

