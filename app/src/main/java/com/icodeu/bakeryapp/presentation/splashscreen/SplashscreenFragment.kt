package com.icodeu.bakeryapp.ui.splashscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.icodeu.bakeryapp.R
import com.icodeu.bakeryapp.ui.ResponseStatus
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashscreenFragment : Fragment() {
    private val splashScreenViewModel: SplashScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splashscreen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        splashScreenViewModel.checkLoggedIn()

        splashScreenViewModel.isLoggedIn.observe(viewLifecycleOwner, {

            when(it.status){
                ResponseStatus.STATUS_LOADING -> {

                }
                ResponseStatus.STATUS_SUCCESS -> {
                    if (it.data == true){
                        findNavController().navigate(R.id.action_splashscreenFragment_to_homeFragment)
                    }else{
                        findNavController().navigate(R.id.action_splashscreenFragment_to_loginFragment)
                    }
                }
                ResponseStatus.STATUS_ERROR -> {

                }
            }
        })
    }
}