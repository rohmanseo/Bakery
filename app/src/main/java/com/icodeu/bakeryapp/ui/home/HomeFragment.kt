package com.icodeu.bakeryapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.icodeu.bakeryapp.MainActivity
import com.icodeu.bakeryapp.R
import com.icodeu.bakeryapp.databinding.FragmentHomeBinding
import com.icodeu.bakeryapp.models.Bread
import com.icodeu.bakeryapp.models.User
import com.icodeu.bakeryapp.ui.ResponseStatus.STATUS_ERROR
import com.icodeu.bakeryapp.ui.ResponseStatus.STATUS_LOADING
import com.icodeu.bakeryapp.ui.ResponseStatus.STATUS_SUCCESS
import com.icodeu.bakeryapp.ui.home.rv_adapters.CarouselAdapter
import com.icodeu.bakeryapp.ui.home.rv_adapters.RecommedRVAdapter
import com.icodeu.bakeryapp.ui.item.ItemFragment
import com.icodeu.bakeryapp.utils.CommonUtils.shortSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), CarouselAdapter.Interaction,
    RecommedRVAdapter.RecommendedItemInterface {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var popularAdapter: CarouselAdapter
    private lateinit var recommendAdapter: RecommedRVAdapter
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getLoggedInUser()
        setupAvatar()
        setupProfileDialog()
        setupPopular()
        setupRecommended()
        setupCardAvatar()
        setupSubscriber()
    }

    private fun setupAvatar() {
        binding.apply {
            Glide.with(requireContext())
                .load(R.drawable.cute_rem)
                .into(imgAvatar)
        }
    }

    private fun setupSubscriber() {
        homeViewModel.user.observe(viewLifecycleOwner, {
            when (it.status) {
                STATUS_LOADING -> {
                    showLoading(true)
                }
                STATUS_SUCCESS -> {
                    if (it.data == null) {
                        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                    } else {
                        it.data?.let { user ->
                            binding.tvUserName.setText("Wecome\n${user.name}")

                        }
                        setupProfileDialog(it.data)
                    }
                    showLoading(false)
                }
                STATUS_ERROR -> {
                    it.error?.let { it1 -> showError(it1) }
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

    private fun setupCardAvatar() {
        binding.apply {
            cardAvatar.setOnClickListener {
                dialog.show()
            }
        }
    }

    fun setupProfileDialog(user: User? = null) {
        val profileView =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_profile, null, false)
        val btnLogout = profileView.findViewById(R.id.btnLogout) as Button
        val btnClose = profileView.findViewById(R.id.btnClose) as ImageButton
        val imgAvatar = profileView.findViewById(R.id.imgAvatar) as ImageView
        val tvName = profileView.findViewById(R.id.tvName) as TextView
        val tvEmail = profileView.findViewById(R.id.tvEmail) as TextView
        Glide.with(requireContext())
            .load(R.drawable.cute_rem)
            .into(imgAvatar)
        tvEmail.setText(user?.email)
        tvName.setText(user?.name)
        btnLogout.setOnClickListener {
            dialog.dismiss()
            homeViewModel.logout()
        }
        btnClose.setOnClickListener {
            dialog.dismiss()
        }
        dialog = MaterialAlertDialogBuilder(requireContext())
            .setView(profileView)
            .create()
    }


    private fun setupPopular() {
        popularAdapter = CarouselAdapter(this)

        homeViewModel.getPopular()
        homeViewModel.popular.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                STATUS_LOADING -> {
                    showLoading(true)
                }
                STATUS_SUCCESS -> {
                    it.data?.let { popular -> popularAdapter.submitList(popular) }
                }
                STATUS_ERROR -> {
                    it.error?.let { message -> showError(message) }
                }
            }
        })

        binding.apply {
            rvPopular.hasFixedSize()
            rvPopular.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            rvPopular.adapter = popularAdapter
        }
    }

    private fun setupRecommended() {
        recommendAdapter = RecommedRVAdapter(this)

        homeViewModel.getRecent()
        homeViewModel.recent.observe(viewLifecycleOwner, Observer {
            println("Recent items ${it}")
            when (it.status) {
                STATUS_LOADING -> {
                    showLoading(true)
                }
                STATUS_SUCCESS -> {
                    it.data?.let { recent -> recommendAdapter.submitList(recent) }
                }
                STATUS_ERROR -> {
                    it.error?.let { message -> showError(message) }
                }
            }
        })

        binding.apply {
            rvRecommended.adapter = recommendAdapter
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onItemSelected(position: Int, item: Bread) {
        ItemFragment().show(childFragmentManager, "")
    }
}