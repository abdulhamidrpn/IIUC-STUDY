package com.rpn.iiucstudy.presentation.ui.course_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rpn.iiucstudy.data.utils.AppPreferences
import com.rpn.iiucstudy.data.utils.DataState
import com.rpn.iiucstudy.domain.use_case.GetCourseVideoData
import com.rpn.iiucstudy.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CourseDetailViewModel(
    private val getCourseVideoData: GetCourseVideoData,
    private val appPreferences: AppPreferences
) : ViewModel() {

    private val _currentTheme: MutableStateFlow<String?> = MutableStateFlow(null)
    val currentTheme = _currentTheme.asStateFlow()


    // Get the string flow from DataStore
    private val _currentHomeTitle: Flow<String> = appPreferences.getHomeTitle()

    // Expose it as a StateFlow to observe in Compose
    val currentHomeTitle: StateFlow<String> = _currentHomeTitle
        .stateIn(viewModelScope, SharingStarted.Lazily, "")


    fun setHomeTitle(value: String) = viewModelScope.launch(Dispatchers.IO) {
        appPreferences.setHomeTitle(value)
    }


    fun deleteHistory() = viewModelScope.launch(Dispatchers.IO) {
        //localNewsRepository.deleteAllBookmark()
    }

    private val _state =
        MutableStateFlow<CourseDetailScreenState>(CourseDetailScreenState(Resource.Loading))
    val state: StateFlow<CourseDetailScreenState> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = CourseDetailScreenState(),
    )

    init {
        onEvent(CourseDetailScreenEvents.LoadCourseVideos)
    }

    fun onEvent(event: CourseDetailScreenEvents) {
        when (event) {
            is CourseDetailScreenEvents.LoadCourseVideos -> {
                viewModelScope.launch(Dispatchers.IO) {
                    getCourseVideos("PLUYYmyIgqFDErQgpigiMUT--s7PFvX8Vn")
                }
            }

            is CourseDetailScreenEvents.SetCurrentVideo -> {
                _state.update { it.copy(currentVideo = event.currentVideo) }
            }
        }
    }

    private suspend fun getCourseVideos(playlistId: String) {

        getCourseVideoData(playlistId)
            .onEach { data ->
                when (data) {
                    DataState.Loading -> {
                        _state.update { it.copy(courseVideo = Resource.Loading) }
                    }

                    is DataState.Success -> {
                        withContext(Dispatchers.Main) {
                            println("CourseDetailViewModel data.data ${data.data}")
                            _state.update {
                                it.copy(
                                    currentVideo = data.data.firstOrNull(),
                                    courseVideo = Resource.Success(data.data)
                                )
                            }
                        }
                    }

                    is DataState.Error -> {
                        _state.update { it.copy(courseVideo = Resource.Error(data.error)) }
                    }
                }
            }.launchIn(viewModelScope)
    }

}