package com.rpn.iiucstudy.di

import com.rpn.iiucstudy.presentation.ui.course_details.CourseDetailViewModel
import com.rpn.iiucstudy.presentation.ui.history.HistoryViewModel
import com.rpn.iiucstudy.presentation.ui.home.HomeViewModel
import com.rpn.iiucstudy.presentation.ui.profile.ProfileViewModel
import com.rpn.iiucstudy.presentation.ui.setting.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val viewmodelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::CourseDetailViewModel)
    viewModelOf(::HistoryViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::SettingViewModel)
}
