package com.icodeu.bakeryapp.ui.register

import android.content.Context
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
import com.icodeu.bakeryapp.databinding.FragmentRegisterBinding
import com.icodeu.bakeryapp.ui.ResponseStatus
import com.icodeu.bakeryapp.ui.dialog.LoadingDialog
import com.icodeu.bakeryapp.utils.CommonUtils.isNotEmpty
import com.icodeu.bakeryapp.utils.CommonUtils.isNotError
import com.icodeu.bakeryapp.utils.CommonUtils.isValidEmail
import com.icodeu.bakeryapp.utils.CommonUtils.shortSnackbar
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.BiFunction
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    val compositeDisposable = CompositeDisposable()
    private val registerViewModel: RegisterViewModel by viewModel()

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
            setupRxBinding()
            setupRegisterButton()
            setupSubscriber()
        }
    }

    private fun setupSubscriber() {
        binding.apply {

            registerViewModel.user.observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    ResponseStatus.STATUS_LOADING -> {
                        showLoading(true)
                    }
                    ResponseStatus.STATUS_SUCCESS -> {
                        if (it.data != null) {
                            findNavController().popBackStack()
                        }
                        showLoading(false)
                    }
                    ResponseStatus.STATUS_ERROR -> {
                        showError(it.error?:"")
                        showLoading(false)
                    }
                }
            })
        }
    }
    fun showLoading(isLoading: Boolean) {
        (activity as MainActivity).showLoading(isLoading)
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

    private fun setupRxBinding() {

        binding.apply {
            val nameSubscriber = edtname.textChanges()
                .skip(1).filter { it.isNotEmpty() }
                .debounce(500, TimeUnit.MILLISECONDS)
            val emailSubscriber = edtEmail.textChanges()
                .skip(1).filter { it.isNotEmpty() }
                .debounce(500, TimeUnit.MILLISECONDS)
            val passwordSubscriber = edtPassword.textChanges()
                .skip(1).filter { it.isNotEmpty() }
                .debounce(500, TimeUnit.MILLISECONDS)
            val passwordReSubscriber = edtRePassword.textChanges()
                .skip(1).filter { it.isNotEmpty() }
                .debounce(500, TimeUnit.MILLISECONDS)


            compositeDisposable.add(
                nameSubscriber
                    .map { it.length < 6 }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (it) {
                            nameOutline.error = "Name must be at least 6 characters"
                        } else {
                            nameOutline.isErrorEnabled = false
                        }
                    }
            )
            compositeDisposable.add(
                emailSubscriber
                    .map { it.toString().isValidEmail() }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (it) {
                            emailOutline.error = "Invalid Email"
                        } else {
                            emailOutline.isErrorEnabled = false
                        }
                    }
            )
            compositeDisposable.add(
                passwordSubscriber
                    .map { it.length < 6 }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (it) {
                            passwordoutline.error = "Password must be at least 6 characters"
                        } else {
                            passwordoutline.isErrorEnabled = false
                            if (edtPassword.text.toString() == edtRePassword.text.toString()){
                                rePasswordoutline.isErrorEnabled = false
                            }
                        }
                    }
            )
            passwordReSubscriber
                .map { it.length < 6 }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it) {
                        rePasswordoutline.error = "Re-type password must be at least 6 characters"
                    } else {
                        rePasswordoutline.isErrorEnabled = false
                    }
                }
            compositeDisposable.add(
                Observable
                    .combineLatest(
                        passwordSubscriber,
                        passwordReSubscriber,
                        BiFunction { _, _ ->
                            edtRePassword.text.toString() != edtPassword.text.toString()
                        })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
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
            )
        }
    }


    fun showPasswordError(text: String) {
        binding.rePasswordoutline.error = text
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
            RegisterFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}