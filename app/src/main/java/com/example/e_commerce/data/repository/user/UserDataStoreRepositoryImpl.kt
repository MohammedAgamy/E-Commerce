package com.example.e_commerce.data.repository.user

import com.example.e_commerce.data.datasurce.datastore.UserPreferenceDataSource
import kotlinx.coroutines.flow.Flow

class UserDataStoreRepositoryImpl(private val userPreferenceDataSource: UserPreferenceDataSource) :
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