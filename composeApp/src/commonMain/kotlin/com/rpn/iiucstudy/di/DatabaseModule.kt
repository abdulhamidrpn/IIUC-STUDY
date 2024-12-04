package com.rpn.iiucstudy.di


import com.rpn.iiucstudy.data.utils.AppPreferences
import com.rpn.iiucstudy.dataStorePreferences
import org.koin.dsl.module

val databaseModule = module {

    // datastore
    single {
        AppPreferences(dataStorePreferences())
    }

}