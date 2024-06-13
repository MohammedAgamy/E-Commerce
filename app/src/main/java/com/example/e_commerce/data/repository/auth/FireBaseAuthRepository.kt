package com.example.e_commerce.data.repository.auth

import com.example.e_commerce.data.model.Recourse
import kotlinx.coroutines.flow.Flow

interface FireBaseAuthRepository {
    suspend fun logInWithEmailAndPassword(email: String, password: String)
            : Flow<Recourse<String>>
}