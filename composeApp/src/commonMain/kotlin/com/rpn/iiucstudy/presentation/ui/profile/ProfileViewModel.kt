package com.rpn.iiucstudy.presentation.ui.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel() : ViewModel() {

    private val viewModelScope = CoroutineScope(Dispatchers.IO)

    private val _state = MutableStateFlow<ProfileScreenState>(ProfileScreenState())
    val state: StateFlow<ProfileScreenState> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ProfileScreenState(),
    )

    init {
        onEvent(ProfileScreenEvents.LoadHomeData)
    }

    fun onEvent(event: ProfileScreenEvents) {
        when (event) {
            is ProfileScreenEvents.LoadHomeData -> {
                viewModelScope.launch {
                    getHeadLineData()
                }
            }
        }
    }

    private suspend fun getHeadLineData() {

    }

}