package com.example.e_commerce.ui.login.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.e_commerce.R
import com.example.e_commerce.data.datasurce.datastore.UserPreferenceDataSource
import com.example.e_commerce.data.repository.user.UserPreferenceRepository
import com.example.e_commerce.data.repository.user.UserRepositoryPreferenceIml
import com.example.e_commerce.databinding.FragmentLogInBinding
import com.example.e_commerce.ui.home.common.UserViewModel
import com.example.e_commerce.ui.login.viewmodel.LogInViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding?=null
    private val binding get() = _binding!!

    val logInViewModel: LogInViewModel by viewModels {
        LogInViewModel.LogInViewModelFactory(
            userPreferenceRepository = UserRepositoryPreferenceIml(
                (UserPreferenceDataSource(
                    requireActivity()
                ))
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =FragmentLogInBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

}