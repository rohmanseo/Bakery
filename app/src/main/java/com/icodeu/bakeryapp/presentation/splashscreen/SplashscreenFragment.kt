package com.icodeu.bakeryapp.presentation.splashscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.icodeu.bakeryapp.R
import com.icodeu.bakeryapp.core.utils.Resource
import com.icodeu.bakeryapp.presentation.MainViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SplashscreenFragment : Fragment() {
    private val mainViewModel: MainViewModel by sharedViewModel()

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

        lifecycleScope.launchWhenStarted {
            mainViewModel.isLoggedIn.collect {
                when (it) {
                    is Resource.Error -> {

                    }
                    is Resource.Success -> {
                        if (it.data == true) {
                            findNavController().navigate(R.id.action_splashscreenFragment_to_homeFragment)
                        } else {
                            findNavController().navigate(R.id.action_splashscreenFragment_to_loginFragment)
                        }
                    }
                    is Resource.Loading -> {

                    }
                }
            }
        }
    }
}