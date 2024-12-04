package com.rpn.iiucstudy.data.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import com.rpn.iiucstudy.domain.model.NavigationHistory
import com.rpn.iiucstudy.presentation.utils.Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AppPreferences(
    private val dataStore: DataStore<Preferences>
) {
    private val themeKey = stringPreferencesKey("theme")

    private val searchHistoryKey = stringPreferencesKey("search_history")

    private val lastScreenKey = stringPreferencesKey("last_screen")

    private val NAVIGATION_HISTORY_KEY = stringPreferencesKey("navigation_history")

    // Function to get the search history as a Flow
    fun getSearchHistoryFlow(): Flow<List<String>> {
        return dataStore.data.map { preferences ->
            preferences[searchHistoryKey]?.split(",")?.take(16) ?: emptyList()
        }
    }

    // Function to add a new search query to the history
    suspend fun addSearchQuery(query: String) {
        dataStore.edit { preferences ->
            val currentList =
                preferences[searchHistoryKey]?.split(",")?.toMutableList() ?: mutableListOf()
            if (query !in currentList) {
                if (currentList.size >= 16) {
                    currentList.removeAt(16) // Remove the oldest item if the list exceeds 16 items
                }
                currentList.add(0, query)
                preferences[searchHistoryKey] = currentList.joinToString(",")
            }
        }
    }

    // Function to clear the search history
    suspend fun clearSearchHistory() {
        dataStore.edit { preferences ->
            preferences.remove(searchHistoryKey)
        }
    }

    suspend fun getTheme(): String {
        return dataStore.data.first()[themeKey] ?: Theme.SYSTEM_DEFAULT.name
    }

    suspend fun setTheme(theme: String) {
        dataStore.edit { preferences ->
            preferences[themeKey] = theme
        }
    }


    // Function to get string from DataStore as a Flow
    fun getHomeTitle(): Flow<String> {
        return dataStore.data
            .map { preferences ->
                preferences[lastScreenKey] ?: "" // Provide a default value if the key doesn't exist
            }
    }

    suspend fun setHomeTitle(title: String) {
        dataStore.edit { preferences ->
            preferences[lastScreenKey] = title
        }
    }







    private val json = Json { encodeDefaults = true }

    val navigationHistory: Flow<List<NavigationHistory>> = dataStore.data
        .map { preferences ->
            preferences[NAVIGATION_HISTORY_KEY]?.let { jsonString ->
                json.decodeFromString<List<NavigationHistory>>(jsonString)
            } ?: emptyList()
        }

    suspend fun saveNavigationHistory(history: List<NavigationHistory>) {
        dataStore.edit { preferences ->
            val jsonString = json.encodeToString(history)
            preferences[NAVIGATION_HISTORY_KEY] = jsonString
        }
    }

}