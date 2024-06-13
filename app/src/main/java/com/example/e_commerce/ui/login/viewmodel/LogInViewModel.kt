package com.example.e_commerce.ui.login.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.model.Recourse
import com.example.e_commerce.data.repository.auth.FireBaseAuthRepository
import com.example.e_commerce.data.repository.user.UserPreferenceRepository
import com.example.e_commerce.data.repository.user.UserDataStoreRepositoryImpl
import com.example.e_commerce.ui.home.common.UserViewModel
import com.example.e_commerce.utils.isValidEmail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception

class LogInViewModel(
    val userPrefer: UserPreferenceRepository,
    val fireBaseAuthRepository: FireBaseAuthRepository
) : ViewModel() {


    val _loginState = MutableSharedFlow<Recourse<String>>()
    val loginState :SharedFlow<Recourse<String>> = _loginState.asSharedFlow()

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    private val isLoginValid: Flow<Boolean> = combine(email, password) { email, password ->
        email.isValidEmail() && password.length >= 6
    }

    fun logIn() {
        viewModelScope.launch {
            val email = email.value
            val password = password.value
            Log.d("TAG", email)

            if (isLoginValid.first()) {
                fireBaseAuthRepository.logInWithEmailAndPassword(email, password)
                    .onEach { recource ->
                        when (recource) {
                           // is Recourse.Loading -> _loginState.emit(Recourse.Loading())
                            is Recourse.Success -> {
                                _loginState.emit(Recourse.Success(recource.data ?: "Empty Id"))
                            }

                            else -> _loginState.emit(recource)
                        }
                    }.launchIn(viewModelScope)
            } else {
                _loginState.emit(Recourse.Error(Exception("Invalid Email or Password  ")))
            }

        }
        Log.d("TAG", "clickedLOgIn")
    }

    fun saveLogInState(isLogin: Boolean) {
        viewModelScope.launch {

        }
    }

    class LogInViewModelFactory(
        private val userPreferenceRepository: UserPreferenceRepository,
        private val fireBaseAuthRepository: FireBaseAuthRepository
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LogInViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST") return LogInViewModel(
                    userPreferenceRepository,
                    fireBaseAuthRepository
                ) as T
            }

            throw IllegalArgumentException("UnKnow ViewModel Class")

        }
    }

}