package com.icodeu.bakeryapp.presentation.splashscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.icodeu.bakeryapp.R
import com.icodeu.bakeryapp.utils.Resource
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


        splashScreenViewModel.isLoggedIn.observe(viewLifecycleOwner, {

            when(it){
                is Resource.Error -> {

                }
                is Resource.Success -> {
                    if (it.data == true){
                        findNavController().navigate(R.id.action_splashscreenFragment_to_homeFragment)
                    }else{
                        findNavController().navigate(R.id.action_splashscreenFragment_to_loginFragment)
                    }
                }
                is Resource.Loading -> {

                }
            }
        })
    }
}