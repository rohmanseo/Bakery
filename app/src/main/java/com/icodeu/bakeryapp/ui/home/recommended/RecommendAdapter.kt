package com.icodeu.bakeryapp.ui.home.recommended

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class RecommendAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
    val MAX_ITEM = 5

    override fun getItemCount(): Int {
        return MAX_ITEM
    }

    override fun createFragment(position: Int): RecommendedFragment {
        return RecommendedFragment()
    }
}