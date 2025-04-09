package com.muhammadfaishalrizqipratama0094.cookit.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class ProfileRepository(private val context: Context) {

    private val nameKey = stringPreferencesKey("user_name")
    private val emailKey = stringPreferencesKey("user_email")

    val profileData = context.dataStore.data.map { preferences ->
        ProfileData(
            name = preferences[nameKey] ?: "",
            email = preferences[emailKey] ?: ""
        )
    }

    suspend fun saveProfile(name: String, email: String) {
        context.dataStore.edit { preferences ->
            preferences[nameKey] = name
            preferences[emailKey] = email
        }
    }
}

data class ProfileData(val name: String, val email: String)