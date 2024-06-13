package com.example.e_commerce.data.repository.auth

import com.example.e_commerce.data.model.Recourse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FireBaseAuthRepositoryImp(
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
) : FireBaseAuthRepository {


    override suspend fun logInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Recourse<String>> = flow {
        try {

            // i load to get data
            emit(Recourse.Loading())
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            //if user not empty get user id
            authResult.user?.let {user->
                emit(Recourse.Success(user.uid))
                // else is empty return not found
            }?:run {
                emit(Recourse.Error(java.lang.Exception(("User Not Found"))))
            }
        } catch (e: Exception) {
            emit(Recourse.Error(e))
        }
    }

}