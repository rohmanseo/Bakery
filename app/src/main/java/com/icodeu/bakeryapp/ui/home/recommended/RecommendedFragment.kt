package com.icodeu.bakeryapp.ui.home.recommended

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.icodeu.bakeryapp.R
import com.icodeu.bakeryapp.databinding.FragmentRecommendedBinding
import com.icodeu.bakeryapp.ui.home.Cake
import com.icodeu.bakeryapp.ui.home.rv_adapters.RecommedRVAdapter
import com.icodeu.bakeryapp.ui.item.ItemFragment

class RecommendedFragment : Fragment(), RecommedRVAdapter.Interaction {

    private lateinit var binding: FragmentRecommendedBinding
    private lateinit var carouselAdapter: RecommedRVAdapter
    private val viewModel: RecommendedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecommendedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carouselAdapter = RecommedRVAdapter(this)
        viewModel.getRecommeds()
        viewModel.recommeded.observe(viewLifecycleOwner, Observer {
            println("I got items ${it.toString()}")
            carouselAdapter.submitList(it)
        })
        binding.apply {
            rvRecommended.hasFixedSize()
            rvRecommended.adapter = carouselAdapter
        }

    }

    override fun onItemSelected(position: Int, item: Cake) {
        ItemFragment().show(childFragmentManager,"")

    }
}