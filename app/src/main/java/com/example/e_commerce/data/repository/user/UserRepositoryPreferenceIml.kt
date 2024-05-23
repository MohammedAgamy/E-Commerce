package com.example.e_commerce.data.repository.user

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.e_commerce.data.datasurce.datastore.DataStoreKeys.IS_USER_LOGGED_IN
import com.example.e_commerce.data.datasurce.datastore.DataStoreKeys.SAVE_USER_ID
import com.example.e_commerce.data.datasurce.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryPreferenceIml(private val context: Context):UserPreferenceRepository {
    override suspend fun saveLogInState(isLogIn:Boolean)
    {
        context.dataStore.edit {preferences->
            preferences[IS_USER_LOGGED_IN] = isLogIn
        }
    }

    override suspend fun saveUserId(userId: String) {
        context.dataStore.edit {preferences->
            preferences[SAVE_USER_ID] = userId
        }
    }

    override suspend fun isUSerLoggedIn(): Flow<Boolean> {
       return  context.dataStore.data
        .map {preferences->
            preferences[IS_USER_LOGGED_IN] ?: false
        }


    }



}