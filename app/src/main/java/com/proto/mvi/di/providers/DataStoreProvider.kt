package com.proto.mvi.di.providers

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_settings")

class DataStoreProvider @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    val dataStore: DataStore<Preferences> = context.dataStore
}