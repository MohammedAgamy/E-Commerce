package com.example.e_commerce.ui.login.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_commerce.R
import com.example.e_commerce.data.repository.user.UserPreferenceRepository
import com.example.e_commerce.data.repository.user.UserRepositoryPreferenceIml
import com.example.e_commerce.databinding.FragmentLogInBinding
import com.example.e_commerce.ui.login.viewmodel.LogInViewModel

class LogInFragment : Fragment() {

    lateinit var binding: FragmentLogInBinding
  val logInViewModel:LogInViewModel by lazy {
      LogInViewModel(userPrefer = UserRepositoryPreferenceIml(requireActivity()))
  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

}