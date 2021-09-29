package com.icodeu.bakeryapp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.icodeu.bakeryapp.MainActivity
import com.icodeu.bakeryapp.R
import com.icodeu.bakeryapp.databinding.FragmentLoginBinding
import com.icodeu.bakeryapp.presentation.ResponseStatus
import com.icodeu.bakeryapp.utils.CommonUtils.isNotEmpty
import com.icodeu.bakeryapp.utils.CommonUtils.isNotError
import com.icodeu.bakeryapp.utils.CommonUtils.isValidEmail
import com.icodeu.bakeryapp.utils.CommonUtils.shortSnackbar
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    val compositeDisposable = CompositeDisposable()
    val loginViewModel: LoginViewModel by viewModel()

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
        loginViewModel.isLoggedIn.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResponseStatus.STATUS_LOADING -> {
                    showLoading(true)
                }
                ResponseStatus.STATUS_SUCCESS -> {
                    if (it.data == true) {
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                    showLoading(false)
                }
                ResponseStatus.STATUS_ERROR -> {
                    showError(it.error!!)
                    showLoading(false)
                }
            }
        })

        loginViewModel.user.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResponseStatus.STATUS_LOADING -> {
                    showLoading(true)
                }
                ResponseStatus.STATUS_SUCCESS -> {
                    if (it.data != null) {
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                    showLoading(false)
                }
                ResponseStatus.STATUS_ERROR -> {
                    showError(it.error!!)
                    showLoading(false)
                }
            }
        })

    }

    fun showLoading(isLoading: Boolean) {
        (activity as MainActivity).showLoading(isLoading)
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

    private fun setupRxBinding() {
        binding.apply {
            val emailSub = edtEmail.textChanges()
                .filter { it.isNotEmpty() }
                .skip(1)
                .debounce(500, TimeUnit.MILLISECONDS)
            val passwordSub = edtPassword.textChanges()
                .filter { it.isNotEmpty() }
                .skip(1)
                .debounce(500, TimeUnit.MILLISECONDS)

            compositeDisposable.add(emailSub
                .map { it.toString().isValidEmail() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it) {
                        emailOutline.error = "Invalid Email"
                    } else {
                        emailOutline.isErrorEnabled = false
                    }
                })
            compositeDisposable.add(
                passwordSub
                    .map { it.length < 6 }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (it) {
                            passwordoutline.error = "Password must be at least 6 characters"
                        } else {
                            passwordoutline.isErrorEnabled = false
                        }
                    }
            )
        }
    }

    private fun login(email: String, password: String) {
        loginViewModel.login(email, password)
    }

    override fun onDestroyView() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onDestroyView()
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