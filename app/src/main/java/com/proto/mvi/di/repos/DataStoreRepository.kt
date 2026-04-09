package com.proto.mvi.di.repos

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        private val DARK_THEME_KEY = booleanPreferencesKey("theme")
    }

    // ✅ Read theme preference
    val isDarkTheme: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[DARK_THEME_KEY] ?: false  // default light
        }
        .catch { _ ->
            emit(false)
        }

    // ✅ Toggle theme
    suspend fun toggleTheme() {
        dataStore.edit { preferences ->
            val current = preferences[DARK_THEME_KEY] ?: false
            preferences[DARK_THEME_KEY] = !current
        }
    }

    // ✅ Set theme
    suspend fun setDarkTheme(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[DARK_THEME_KEY] = enabled
        }
    }
}