package com.example.e_commerce.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.repository.user.UserPreferenceRepository
import kotlinx.coroutines.launch

class LogInViewModel(
    val userPrefer: UserPreferenceRepository
) : ViewModel() {

    fun saveLogInState(isLogin:Boolean)
    {
        viewModelScope.launch{

        }
    }

}