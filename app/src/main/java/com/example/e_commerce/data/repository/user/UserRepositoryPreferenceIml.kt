package com.example.e_commerce.data.repository.user

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.e_commerce.data.datasurce.datastore.DataStoreKeys.IS_USER_LOGGED_IN
import com.example.e_commerce.data.datasurce.datastore.DataStoreKeys.SAVE_USER_ID
import com.example.e_commerce.data.datasurce.datastore.UserPreferenceDataSource
import com.example.e_commerce.data.datasurce.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryPreferenceIml(private val userPreferenceDataSource: UserPreferenceDataSource) :
    UserPreferenceRepository {
    override suspend fun saveLogInState(isLogIn: Boolean) {
        userPreferenceDataSource.saveLogInState(isLogIn)
    }

    override suspend fun saveUserId(userId: String) {
        userPreferenceDataSource.saveUserId(userId)
    }

    override suspend fun getUserId(): Flow<String> {
        return userPreferenceDataSource.userId
    }

    override suspend fun isUSerLoggedIn(): Flow<Boolean> {
        return userPreferenceDataSource.isUserLoggedIn
    }


}