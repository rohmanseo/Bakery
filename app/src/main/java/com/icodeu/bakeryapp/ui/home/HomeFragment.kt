package com.icodeu.bakeryapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.icodeu.bakeryapp.MainActivity
import com.icodeu.bakeryapp.R
import com.icodeu.bakeryapp.databinding.FragmentHomeBinding
import com.icodeu.bakeryapp.models.User
import com.icodeu.bakeryapp.ui.home.recommended.RecommendAdapter
import com.icodeu.bakeryapp.ui.home.rv_adapters.CarouselAdapter
import com.icodeu.bakeryapp.ui.item.ItemFragment
import com.icodeu.bakeryapp.utils.CommonUtils.shortSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), CarouselAdapter.Interaction {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var popularAdapter: CarouselAdapter
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var recommendAdapter: RecommendAdapter
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
        setupProfileDialog()
        setupPopular()
        setupRecommended()
        setupCardAvatar()
        setupSubscriber()
    }

    private fun setupSubscriber() {
        homeViewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                (activity as MainActivity).showLoading(true)
            } else {
                (activity as MainActivity).showLoading(false)
            }
        })
        homeViewModel.error.observe(viewLifecycleOwner, Observer {
            if (it.isError) {
                requireView().shortSnackbar(it.errorMessage.toString())
            }
        })
        homeViewModel.user.observe(viewLifecycleOwner, Observer {
            if (it == null){
                findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            }else{
                binding.tvUserName.setText("Wecome\n${it.name}")
                setupProfileDialog(it)
            }
        })
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
        val tvName = profileView.findViewById(R.id.tvName) as TextView
        val tvEmail = profileView.findViewById(R.id.tvEmail) as TextView
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

    private fun setupRecommended() {
        recommendAdapter = RecommendAdapter(requireActivity())
        (0..5).forEach {
            recommendAdapter.createFragment(it)
        }
        binding.apply {

            vpRecommended.adapter = recommendAdapter
            tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
            TabLayoutMediator(tabLayout, vpRecommended) { tab, position ->
                tab.text = "Cake Four ye"
            }.attach()
        }
    }


    private fun setupPopular() {
        popularAdapter = CarouselAdapter(this)

        homeViewModel.getPopular()
        homeViewModel.popular.observe(viewLifecycleOwner, Observer {
            popularAdapter.submitList(it)
        })

        binding.apply {
            rvPopular.hasFixedSize()
            rvPopular.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            rvPopular.adapter = popularAdapter
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

    override fun onItemSelected(position: Int, item: Cake) {
        ItemFragment().show(childFragmentManager, "")
    }
}