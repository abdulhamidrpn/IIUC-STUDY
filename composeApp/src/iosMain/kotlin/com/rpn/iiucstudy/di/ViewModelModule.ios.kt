package com.rpn.iiucstudy.di

import com.rpn.iiucstudy.presentation.ui.course_details.CourseDetailViewModel
import org.koin.core.module.Module
import com.rpn.iiucstudy.presentation.ui.home.HomeViewModel
import com.rpn.iiucstudy.presentation.ui.history.HistoryViewModel
import com.rpn.iiucstudy.presentation.ui.profile.ProfileViewModel
import com.rpn.iiucstudy.presentation.ui.setting.SettingViewModel

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


actual val viewmodelModule = module {
    factory {
        HomeViewModel(get(),get())
    }
    factory {
        CourseDetailViewModel(get(),get())
    }
    factory {
        ProfileViewModel()
    }
    factory {
        HistoryViewModel()
    }
    factory {
        SettingViewModel(get())
    }
}