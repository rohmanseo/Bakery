package com.icodeu.bakeryapp.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.icodeu.bakeryapp.databinding.FragmentHomeBinding
import com.icodeu.bakeryapp.ui.home.popular.PopularViewModel
import com.icodeu.bakeryapp.ui.home.recommended.RecommendAdapter

class HomeFragment : Fragment(), CarouselAdapter.Interaction {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var popularAdapter: CarouselAdapter
    private val viewmodel: PopularViewModel by viewModels()
    private lateinit var recommendAdapter: RecommendAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        binding = FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPopular()
        setupRecommended()

    }

    private fun setupRecommended() {
        recommendAdapter = RecommendAdapter(requireActivity())
        (0..5).forEach{
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

        viewmodel.getPopular()
        viewmodel.popular.observe(viewLifecycleOwner, Observer {
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

    }
}