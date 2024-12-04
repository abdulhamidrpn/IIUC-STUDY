package com.rpn.iiucstudy.presentation.ui.profile

import com.rpn.iiucstudy.domain.model.Article
import com.rpn.iiucstudy.utils.Resource

data class ProfileScreenState(
    val articles: Resource<List<Article>> = Resource.Idle
)

