package com.rpn.iiucstudy.data.repository

import com.rpn.iiucstudy.data.mapper.toDomainCourseVideoModelList
import com.rpn.iiucstudy.data.remote.dto.youtube.YoutubePlaylistDto
import com.rpn.iiucstudy.data.utils.Constants
import com.rpn.iiucstudy.data.utils.DataState
import com.rpn.iiucstudy.domain.model.CourseVideo
import com.rpn.iiucstudy.domain.repository.CourseDetailRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CourseDetailRepositoryImpl(
    private val client: HttpClient
) : CourseDetailRepository {
    override suspend fun getCourseVideos(playlistId: String): Flow<DataState<List<CourseVideo>>> =
        flow {
            try {
                emit(DataState.Loading)
                val response =
                    client.get("https://www.googleapis.com/youtube/v3/playlistItems") {
                        parameter("part", "snippet")
                        parameter("playlistId", playlistId) // The playlist ID
                        parameter("key", Constants.API_KEY) // Your API key
                        parameter("maxResults", 30) // Maximum results to fetch
                    }.body<YoutubePlaylistDto>()

                val courseVideos = response.items.toDomainCourseVideoModelList()

                emit(DataState.Success(courseVideos))
            } catch (e: Exception) {
                emit(DataState.Error(e.message ?: "Unknown error"))
            }
        }
}
