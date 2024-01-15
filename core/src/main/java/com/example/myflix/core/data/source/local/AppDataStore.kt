package com.example.myflix.core.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDataStore(
    private val context: Context
) {

    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "myflix.pb")

    suspend fun <T> storeData(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preference ->
            preference[key] = value
        }
    }

    suspend fun clear() {
        context.dataStore.edit { preference ->
            preference.remove(TOKEN)
        }
    }

    val token: Flow<String>
        get() = context.dataStore.data.map { preference ->
            preference[TOKEN] ?: ""
        }

    companion object {
        val TOKEN = stringPreferencesKey("TOKEN")
    }
}