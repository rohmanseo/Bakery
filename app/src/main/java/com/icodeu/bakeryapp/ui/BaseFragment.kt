package com.icodeu.bakeryapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.icodeu.bakeryapp.MainActivity


abstract class BaseFragment<ActivityClass> : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }
}