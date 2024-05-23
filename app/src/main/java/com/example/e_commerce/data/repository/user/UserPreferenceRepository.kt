package com.example.e_commerce.data.repository.user

import kotlinx.coroutines.flow.Flow

interface UserPreferenceRepository {


   suspend fun isUSerLoggedIn(): Flow<Boolean>
    suspend fun saveLogInState(isLogIn:Boolean)
    suspend fun saveUserId(userId:String)
}