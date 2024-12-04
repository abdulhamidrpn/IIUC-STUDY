package com.rpn.iiucstudy.data.utils

import com.rpn.iiucstudy.BuildKonfig
import com.rpn.iiucstudy.data.remote.dto.SemesterDto


object Constants {

    const val LOG_TAG = "KMP_App"
    const val DB_NAME = "myNewsDB"

    const val dataStoreFileName = "setting.preferences_pb"

    private val API_KEY_GOOGLE_SHEET = BuildKonfig.API_KEY
    val BASE_URL_GOOGLE_SHEET = "https://script.google.com/macros/s/$API_KEY_GOOGLE_SHEET/exec"

    const val ITEMS_PER_PAGE = 20

    const val BASE_URL = "https://www.googleapis.com/youtube/v3/"
    const val API_KEY = "AIzaSyATK5cfxRwEFXlp73Su6HrExL5_6Z0puYw"
    const val TIMEOUT = 10000L
    const val VIDEO_URL = "https://www.youtube.com/watch?v="

    /*
    * This keywords are prohibited
    * If any image related to this keyword shows in feed that will ber removed from list.
    * and also nobody can search related to this keyword.
    * Filtering Halal Content.
    */
    val prohibitedKeywords =
        listOf(
            "woman", "adult", "naughty", "sex", "porn", "lady", "girl", "nude",
            "bikini", "lingerie", "swimsuit", "underwear", "gay", "lesbian",
            "erotic", "provocative", "seductive", "flirt", "romance", "kissing",
            "xxx", "explicit", "hardcore",
            "scantily clad", "revealing", "intimate", "sensual", "adult film"
        )


    val semesters = listOf(
        SemesterDto(
            title = "1st Semester",
            description = "Introduction to the basics.",
            color = "#A8D5BA" // Light Mint Green
        ),
        SemesterDto(
            title = "2nd Semester",
            description = "Building foundational knowledge.",
            color = "#B7C7E0" // Soft Periwinkle Blue
        ),
        SemesterDto(
            title = "3rd Semester",
            description = "Intermediate concepts and applications.",
            color = "#F7CAC9" // Soft Blush Pink
        ),
        SemesterDto(
            title = "4th Semester",
            description = "Deepening understanding.",
            color = "#C8D6E5" // Light Sky Blue
        ),
        SemesterDto(
            title = "5th Semester",
            description = "Advanced topics and exploration.",
            color = "#F0D9B5" // Pale Gold
        ),
        SemesterDto(
            title = "6th Semester",
            description = "Specialized studies and projects.",
            color = "#E5E7E9" // Soft Gray
        ),
        SemesterDto(
            title = "7th Semester",
            description = "Pre-final preparations.",
            color = "#F1E1A6" // Light Pastel Yellow
        ),
        SemesterDto(
            title = "8th Semester",
            description = "Final semester with thesis work.",
            color = "#C5E3BF" // Soft Sage Green
        )
    )

}