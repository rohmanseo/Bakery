package com.icodeu.bakeryapp.presentation.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.icodeu.bakeryapp.R
import com.icodeu.bakeryapp.core.utils.Resource
import com.icodeu.bakeryapp.databinding.FragmentRegisterBinding
import com.icodeu.bakeryapp.presentation.MainViewModel
import com.icodeu.bakeryapp.utils.CommonUtils.isNotEmpty
import com.icodeu.bakeryapp.utils.CommonUtils.isNotError
import com.icodeu.bakeryapp.utils.CommonUtils.isValidEmail
import com.icodeu.bakeryapp.utils.CommonUtils.shortSnackbar
import com.icodeu.bakeryapp.utils.collectWhenStarted
import com.icodeu.bakeryapp.utils.flowBinding.textChanges
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private val registerViewModel: RegisterViewModel by viewModel()
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
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    @FlowPreview
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            Glide.with(requireActivity())
                .load(R.drawable.baker)
                .centerCrop()
                .into(imgBaker)


            tvLogin.setOnClickListener {
                findNavController().popBackStack()
            }
            setupFlowBinding()
            setupRegisterButton()
            setupSubscriber()
        }
    }

    private fun setupSubscriber() {
        binding.apply {
            registerViewModel.user.collectWhenStarted(this@RegisterFragment) {
                when (it) {
                    is Resource.Loading -> {
                        showLoading(true)
                    }
                    is Resource.Success -> {
                        if (it.data != null) {
                            findNavController().popBackStack()
                        }
                        showLoading(false)
                    }
                    is Resource.Error -> {
                        showError(it.error ?: "")
                        showLoading(false)
                    }
                }
            }
        }
    }

    fun showLoading(isLoading: Boolean) {
        mainViewModel.showDialog(isLoading)
    }

    fun showError(errorMessage: String) {
        requireView().shortSnackbar(errorMessage ?: "Error")
    }

    private fun setupRegisterButton() {
        binding.apply {
            btnSignUp.setOnClickListener {
                val isNotError = isNotError(
                    emailOutline,
                    nameOutline,
                    passwordoutline,
                    rePasswordoutline
                )
                val isNotEmpty = isNotEmpty(
                    edtname,
                    edtPassword,
                    edtEmail,
                    edtRePassword
                )
                if (isNotError && isNotEmpty
                ) {
                    register(
                        edtname.text.toString(),
                        edtEmail.text.toString(),
                        edtPassword.text.toString(),
                        edtRePassword.text.toString()
                    )
                } else {
                    requireView().shortSnackbar("Error")
                }
            }
        }
    }

    private fun register(
        name: String,
        email: String,
        password: String,
        password_confirmation: String
    ) {
        registerViewModel.register(
            name = name,
            email = email,
            password = password,
            password_confirmation = password_confirmation
        )

    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun setupFlowBinding() {

        binding.apply {
            val nameSubscriber = edtname.textChanges().map { it.toString() }
                .drop(1).filter { it.isNotEmpty() }
                .debounce(500)
            val emailSubscriber = edtEmail.textChanges().map { it.toString() }
                .drop(1).filter { it.isNotEmpty() }
                .debounce(500)
            val passwordSubscriber = edtPassword.textChanges().map { it.toString() }
                .drop(1).filter { it.isNotEmpty() }
                .debounce(500)
            val passwordReSubscriber = edtRePassword.textChanges().map { it.toString() }
                .drop(1).filter { it.isNotEmpty() }
                .debounce(500)

            nameSubscriber
                .map { it.length < 6 }
                .collectWhenStarted(this@RegisterFragment) {
                    if (it) {
                        nameOutline.error = "Name must be at least 6 characters"
                    } else {
                        nameOutline.isErrorEnabled = false
                    }
                }
            emailSubscriber
                .map { it.isValidEmail() }
                .collectWhenStarted(this@RegisterFragment) {
                    if (it) {
                        emailOutline.error = "Invalid Email"
                    } else {
                        emailOutline.isErrorEnabled = false
                    }
                }
            passwordSubscriber
                .map { it.length < 6 }
                .collectWhenStarted(this@RegisterFragment) {
                    if (it) {
                        passwordoutline.error = "Password must be at least 6 characters"
                    } else {
                        passwordoutline.isErrorEnabled = false
                        if (edtPassword.text.toString() == edtRePassword.text.toString()) {
                            rePasswordoutline.isErrorEnabled = false
                        }
                    }
                }
            passwordReSubscriber
                .map { it.length < 6 }
                .collectWhenStarted(this@RegisterFragment)
                {
                    if (it) {
                        rePasswordoutline.error = "Re-type password must be at least 6 characters"
                    } else {
                        rePasswordoutline.isErrorEnabled = false
                    }
                }
            passwordSubscriber.combine(passwordReSubscriber) { password, rePassWord ->
                password != rePassWord
            }.collectWhenStarted(this@RegisterFragment) {
                if (it) {
                    if (edtRePassword.text.toString().length < 6) {
                        rePasswordoutline.error =
                            "Re-type password must be at least 6 characters\nPassword does not match"
                    } else {
                        rePasswordoutline.error = "Re-type password does not match"
                    }
                } else {
                    rePasswordoutline.isErrorEnabled = false
                }
            }
        }
    }


    fun showPasswordError(text: String) {
        binding.rePasswordoutline.error = text
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}