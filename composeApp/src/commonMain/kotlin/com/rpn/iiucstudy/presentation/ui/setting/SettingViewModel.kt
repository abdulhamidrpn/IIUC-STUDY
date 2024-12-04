package com.rpn.iiucstudy.presentation.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rpn.iiucstudy.data.utils.AppPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SettingViewModel(
    private val appPreferences: AppPreferences
) : ViewModel() {

    private val _currentTheme: MutableStateFlow<String?> = MutableStateFlow(null)
    val currentTheme = _currentTheme.asStateFlow()


    // Get the string flow from DataStore
    private val _currentHomeTitle: Flow<String> = appPreferences.getHomeTitle()

    // Expose it as a StateFlow to observe in Compose
    val currentHomeTitle: StateFlow<String> = _currentHomeTitle
        .stateIn(viewModelScope, SharingStarted.Lazily, "")

    fun deleteHistory() = viewModelScope.launch(Dispatchers.IO) {
        //localNewsRepository.deleteAllBookmark()
    }

    init {
        currentThemeGet()
    }


    private fun currentThemeGet() = runBlocking {
        _currentTheme.value = appPreferences.getTheme()
    }

    fun changeThemeMode(value: String) = viewModelScope.launch(Dispatchers.IO) {
        appPreferences.setTheme(value)
        _currentTheme.value = value
    }


    fun setHomeTitle(value: String) = viewModelScope.launch(Dispatchers.IO) {
        appPreferences.setHomeTitle(value)
    }

}