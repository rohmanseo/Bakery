package com.icodeu.bakeryapp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.icodeu.bakeryapp.R
import com.icodeu.bakeryapp.databinding.FragmentLoginBinding
import com.icodeu.bakeryapp.presentation.MainViewModel
import com.icodeu.bakeryapp.utils.CommonUtils.isNotEmpty
import com.icodeu.bakeryapp.utils.CommonUtils.isNotError
import com.icodeu.bakeryapp.utils.CommonUtils.isValidEmail
import com.icodeu.bakeryapp.utils.CommonUtils.shortSnackbar
import com.icodeu.bakeryapp.utils.Resource
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    val compositeDisposable = CompositeDisposable()
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
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.isLoggedIn.collect {
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
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.user.collect {
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
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.login(email, password)
            }
        }
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