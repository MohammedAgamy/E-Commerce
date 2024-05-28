package com.example.e_commerce.data.datasurce.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferenceDataSource (val context:Context){

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

     suspend fun saveLogInState(isLogIn:Boolean)
    {
        context.dataStore.edit {preferences->
            preferences[DataStoreKeys.IS_USER_LOGGED_IN] = isLogIn
        }
    }

     suspend fun saveUserId(userId: String) {
        context.dataStore.edit {preferences->
            preferences[DataStoreKeys.SAVE_USER_ID] = userId
        }
    }

     val  isUserLoggedIn: Flow<Boolean>  = context.dataStore.data
            .map {preferences->
                preferences[DataStoreKeys.IS_USER_LOGGED_IN] ?: false
    }



    val userId:Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[DataStoreKeys.SAVE_USER_ID].toString()
        }


}