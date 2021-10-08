package com.icodeu.bakeryapp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.icodeu.bakeryapp.R
import com.icodeu.bakeryapp.core.utils.Resource
import com.icodeu.bakeryapp.databinding.FragmentLoginBinding
import com.icodeu.bakeryapp.presentation.MainViewModel
import com.icodeu.bakeryapp.utils.CommonUtils.isNotEmpty
import com.icodeu.bakeryapp.utils.CommonUtils.isNotError
import com.icodeu.bakeryapp.utils.CommonUtils.isValidEmail
import com.icodeu.bakeryapp.utils.CommonUtils.shortSnackbar
import kotlinx.coroutines.flow.collect
import com.icodeu.bakeryapp.utils.collectWhenStarted
import com.icodeu.bakeryapp.utils.flowBinding.textChanges
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    val loginViewModel: LoginViewModel by viewModel()
    private val mainViewModel: MainViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            Glide.with(requireActivity())
                .load(R.drawable.baker)
                .centerCrop()
                .into(imgBaker)


            tvRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

            setupRxBinding()
            setupLoginButton()
            setupSubscriber()

        }
    }

    private fun setupSubscriber() {
        mainViewModel.isLoggedIn.collectWhenStarted(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    if (it.data == true) {
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                }
                is Resource.Error -> {
                    showError(it.error!!)
                    showLoading(false)
                }
            }
        }

        lifecycleScope.launch {
            loginViewModel.user.collectWhenStarted(this@LoginFragment) {
                when (it) {
                    is Resource.Loading -> {
                        showLoading(true)
                    }
                    is Resource.Success -> {
                        showLoading(false)
                    }
                    is Resource.Error -> {
                        showError(it.error!!)
                        showLoading(false)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        println("On Resume")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("On Destroy")
    }

    override fun onStop() {
        super.onStop()
        println("On Stop")
    }

    override fun onStart() {
        super.onStart()
        println("On Start")
    }


    fun showLoading(isLoading: Boolean) {
        mainViewModel.showDialog(isLoading)
    }

    fun showError(errorMessage: String) {
        requireView().shortSnackbar(errorMessage ?: "Error")
    }

    private fun setupLoginButton() {
        binding.apply {
            btnLogin.setOnClickListener {
                val isNotEmpty = isNotEmpty(edtEmail, edtPassword)
                val isNotError = isNotError(emailOutline, passwordoutline)

                if (isNotEmpty && isNotError) {
                    login(edtEmail.text.toString(), edtPassword.text.toString())
                } else {
                    requireView().shortSnackbar("Error")
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    private fun setupRxBinding() {
        binding.apply {
            val emailSub = edtEmail.textChanges()
                .map { it.toString() }
                .filter { it.isNotEmpty() }
                .drop(1)
                .debounce(500)
            val passwordSub = edtPassword.textChanges()
                .filter { it.isNotEmpty() }
                .drop(1)
                .debounce(500)

            emailSub
                .map { it.isValidEmail() }
                .collectWhenStarted(this@LoginFragment) {
                    if (it) {
                        emailOutline.error = "Invalid Email"
                    } else {
                        emailOutline.isErrorEnabled = false
                    }
                }
            passwordSub
                .map { it.length < 6 }
                .collectWhenStarted(this@LoginFragment) {
                    if (it) {
                        passwordoutline.error = "Password must be at least 6 characters"
                    } else {
                        passwordoutline.isErrorEnabled = false
                    }
                }
        }
    }

    private fun login(email: String, password: String) {
        loginViewModel.login(email, password)
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}