package com.rpn.iiucstudy.presentation.ui.history

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HistoryViewModel() : ViewModel() {

    private val viewModelScope = CoroutineScope(Dispatchers.IO)

    private val _state = MutableStateFlow<HistoryScreenState>(HistoryScreenState())
    val state: StateFlow<HistoryScreenState> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = HistoryScreenState(),
    )

    init {
        onEvent(HistoryScreenEvents.LoadHomeData)
    }

    fun onEvent(event: HistoryScreenEvents) {
        when (event) {
            is HistoryScreenEvents.LoadHomeData -> {
                viewModelScope.launch {
                    getHeadLineData()
                }
            }
        }
    }

    private suspend fun getHeadLineData() {

    }

}