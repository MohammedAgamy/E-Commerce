package com.example.e_commerce.ui.home.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.datasurce.datastore.UserPreferenceDataSource
import com.example.e_commerce.data.repository.user.UserPreferenceRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserViewModel(private val userPreferenceRepository:UserPreferenceRepository):ViewModel() {


    //check if user log in or not
    suspend fun isUserLogin() = userPreferenceRepository.isUSerLoggedIn()

  /*  fun setIsLogIn(b:Boolean){
        viewModelScope.launch (IO){
            userPreferenceRepository.saveLogInState(b)
        }
    }
*/

    class UserViewModelFactory(private val userPreferenceRepository:UserPreferenceRepository):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java))
            {
                @Suppress("UNCHECKED_CAST") return UserViewModel(userPreferenceRepository) as T
            }

            throw IllegalArgumentException("UnKnow ViewModel Class")

        }
    }


}