package com.rpn.iiucstudy.di

import com.rpn.iiucstudy.data.utils.Constants
import com.rpn.iiucstudy.data.utils.getHttpClient
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {
    single { getHttpClient(Constants.BASE_URL_GOOGLE_SHEET) }
}