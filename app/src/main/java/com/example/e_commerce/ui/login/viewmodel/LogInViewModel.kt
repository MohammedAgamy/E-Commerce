package com.example.e_commerce.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.repository.user.UserPreferenceRepository
import com.example.e_commerce.data.repository.user.UserRepositoryPreferenceIml
import com.example.e_commerce.ui.home.common.UserViewModel
import kotlinx.coroutines.launch

class LogInViewModel(
    val userPrefer: UserPreferenceRepository
) : ViewModel() {

    fun saveLogInState(isLogin:Boolean)
    {
        viewModelScope.launch{

        }
    }

    class LogInViewModelFactory(private val userPreferenceRepository: UserRepositoryPreferenceIml):
        ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java))
            {
                @Suppress("UNCHECKED_CAST") return UserViewModel(userPreferenceRepository) as T
            }

            throw IllegalArgumentException("UnKnow ViewModel Class")

        }
    }

}