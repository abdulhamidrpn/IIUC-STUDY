package com.rpn.iiucstudy.di

import com.rpn.iiucstudy.data.repository.CourseDetailRepositoryImpl
import com.rpn.iiucstudy.data.repository.HomeRepositoryImpl
import com.rpn.iiucstudy.domain.repository.CourseDetailRepository
import com.rpn.iiucstudy.domain.repository.HomeRepository
import com.rpn.iiucstudy.domain.use_case.GetCourseVideoData
import com.rpn.iiucstudy.domain.use_case.GetHomeData
import org.koin.dsl.module


val repositoryModule = module {

    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single<CourseDetailRepository> {CourseDetailRepositoryImpl(get()) }
}

val domainModule = module {
    factory<GetCourseVideoData> { GetCourseVideoData(get()) }
    factory<GetHomeData> { GetHomeData(get()) }
}
