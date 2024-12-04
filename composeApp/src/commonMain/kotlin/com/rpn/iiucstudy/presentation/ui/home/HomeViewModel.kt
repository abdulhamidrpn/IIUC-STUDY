package com.rpn.iiucstudy.presentation.ui.home

import androidx.lifecycle.ViewModel
import com.rpn.iiucstudy.data.utils.AppPreferences
import com.rpn.iiucstudy.data.utils.DataState
import com.rpn.iiucstudy.domain.model.NavigationHistory
import com.rpn.iiucstudy.domain.model.getValueForDirectory
import com.rpn.iiucstudy.domain.use_case.GetHomeData
import com.rpn.iiucstudy.presentation.navigation.HomeScreenRoutes
import com.rpn.iiucstudy.presentation.utils.capitalizeFirstLetter
import com.rpn.iiucstudy.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getHomeData: GetHomeData,
    private val appPreferences: AppPreferences
) : ViewModel() {
    private val viewModelScope = CoroutineScope(Dispatchers.IO)

    private val _state = MutableStateFlow<HomeScreenState>(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = HomeScreenState(),
    )

    private var updateNavigationHistory = emptyList<NavigationHistory>()

    init {
        getTheme()
        getNavigationHistory()
        onEvent(HomeScreenEvents.LoadDepartmentData)
    }

    private fun getTheme() {
        viewModelScope.launch {
            _state.update { it.copy(theme = appPreferences.getTheme()) }
        }
    }

    fun onEvent(event: HomeScreenEvents) {
        when (event) {
            HomeScreenEvents.LoadDepartmentData -> {
                viewModelScope.launch {
                    getHomeDepartmentData()
                }
            }

            HomeScreenEvents.LoadSemesterData -> {
                viewModelScope.launch {
                    _state.update { it.copy(semesterList = Resource.Success(getHomeData.semesterList)) }
                }
            }

            is HomeScreenEvents.LoadCourseData -> {
                val department =
                    updateNavigationHistory.getValueForDirectory(HomeScreenRoutes.Department.route)
                val semester =
                    updateNavigationHistory.getValueForDirectory(HomeScreenRoutes.Semester.route)
                        .split(" ").firstOrNull() ?: ""
                viewModelScope.launch(Dispatchers.IO) {
                    getHomeCoursesData(department, semester)
                }
            }

            HomeScreenEvents.PullToRefresh -> {
                viewModelScope.launch {
                    _state.update { it.copy(pullRefresh = true) }
                    getHomeDepartmentData()
                    _state.update { it.copy(pullRefresh = false) }
                }
            }

            is HomeScreenEvents.AddToBackStack -> {
                saveOrUpdateNavigationHistory(event.navigationHistory)
            }

            is HomeScreenEvents.RemoveBackStack -> {
                removeNavigationHistory(event.directory)
            }

            is HomeScreenEvents.UpdateHomeTitle -> {
                viewModelScope.launch {
                    appPreferences.setHomeTitle(event.directory.capitalizeFirstLetter())
                }
            }
        }
    }

    private suspend fun getHomeDepartmentData() {
        getHomeData()
            .onEach { data ->
                when (data) {
                    DataState.Loading -> {
                        _state.update { it.copy(departmentList = Resource.Loading) }
                    }

                    is DataState.Success -> {
                        withContext(Dispatchers.Main) {
                            _state.update { it.copy(departmentList = Resource.Success(data.data)) }
                        }
                    }

                    is DataState.Error -> {
                        _state.update { it.copy(departmentList = Resource.Error(data.error)) }
                    }
                }
            }.launchIn(viewModelScope)

    }

    private suspend fun getHomeCoursesData(department: String, semester: String) {
        getHomeData(department, semester)
            .onEach { data ->
                when (data) {
                    DataState.Loading -> {
                        _state.update { it.copy(courseList = Resource.Loading) }
                    }

                    is DataState.Success -> {
                        withContext(Dispatchers.Main) {
                            _state.update { it.copy(courseList = Resource.Success(data.data)) }
                        }
                    }

                    is DataState.Error -> {
                        _state.update { it.copy(courseList = Resource.Error(data.error)) }
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun getNavigationHistory() {
        viewModelScope.launch {
            appPreferences.navigationHistory.collect { history ->
                updateNavigationHistory = history
                _state.update { it.copy(navigationHistory = history) }
            }
        }
    }

    private fun saveOrUpdateNavigationHistory(newHistory: NavigationHistory) {
        viewModelScope.launch {
            val updatedList = updateNavigationHistory.toMutableList().apply {
                val index = indexOfFirst { it.directory == newHistory.directory }
                if (index != -1) {
                    this[index] = newHistory
                } else {
                    add(newHistory)
                }
            }
            appPreferences.saveNavigationHistory(updatedList)

        }
    }

    private fun removeNavigationHistory(directory: String? = null) {
        viewModelScope.launch {
            if (directory.isNullOrEmpty()) {
                appPreferences.saveNavigationHistory(emptyList())
            } else {
                val updatedList = updateNavigationHistory.toMutableList()
                    .filterNot { it.directory == directory }
                appPreferences.saveNavigationHistory(updatedList)
            }
        }
    }

}