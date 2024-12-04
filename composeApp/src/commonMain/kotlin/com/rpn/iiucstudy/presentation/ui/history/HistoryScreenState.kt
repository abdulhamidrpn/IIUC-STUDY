package com.rpn.iiucstudy.presentation.ui.history

import com.rpn.iiucstudy.domain.model.Article
import com.rpn.iiucstudy.utils.Resource

data class HistoryScreenState(
    val articles: Resource<List<Article>> = Resource.Idle
)

