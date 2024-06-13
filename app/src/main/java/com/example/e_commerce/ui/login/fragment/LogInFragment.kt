package com.example.e_commerce.ui.login.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.e_commerce.data.datasurce.datastore.UserPreferenceDataSource
import com.example.e_commerce.data.model.Recourse
import com.example.e_commerce.data.repository.auth.FireBaseAuthRepositoryImp
import com.example.e_commerce.data.repository.user.UserDataStoreRepositoryImpl
import com.example.e_commerce.databinding.FragmentLogInBinding
import com.example.e_commerce.ui.home.common.views.ProgressDialog
import com.example.e_commerce.ui.login.viewmodel.LogInViewModel
import com.google.rpc.context.AttributeContext.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LogInFragment : Fragment() {

    val progresDilalog by lazy { ProgressDialog.createProgressDialog(requireActivity()) }

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    val logInViewModel: LogInViewModel by viewModels {
        LogInViewModel.LogInViewModelFactory(
            userPreferenceRepository = UserDataStoreRepositoryImpl(
                (UserPreferenceDataSource(
                    requireActivity()
                ))
            ),
            fireBaseAuthRepository = FireBaseAuthRepositoryImp()
        )
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = logInViewModel
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iniViewModel()
        iniListeners()
    }

    private fun iniListeners() {
        binding.idAuthLoginBtn.setOnClickListener {
            logInViewModel.logIn()

        }
    }

    private fun iniViewModel() {
        lifecycleScope.launch {
            logInViewModel.loginState.collect() { resource ->
                Log.d("TAG", "iniViewModel $resource")
                resource.let { resources ->
                    when (resources) {
                        is Recourse.Loading -> {
                            progresDilalog.show()
                        }

                        is Recourse.Success -> {
                            progresDilalog.dismiss()
                            Toast.makeText(
                                requireContext(),
                                resources.data ,
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        is Recourse.Error -> {
                            progresDilalog.dismiss()
                            Toast.makeText(
                                requireContext(),
                                resources.exception?.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }

        }
    }

}