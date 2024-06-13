package com.example.e_commerce.data.datasurce.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.e_commerce.data.datasurce.datastore.DataStoreKeys.E_Commerce_Preference
import java.util.prefs.Preferences

object DataStoreKeys {
    const val E_Commerce_Preference ="E_Commerce_Preference"
    val IS_USER_LOGGED_IN= booleanPreferencesKey("is_user_logged_in")
    val SAVE_USER_ID= stringPreferencesKey("user_id")

}
//create file shared to store data
val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = E_Commerce_Preference)
