package com.icodeu.bakeryapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.icodeu.bakeryapp.databinding.ActivityMainBinding
import com.icodeu.bakeryapp.ui.dialog.LoadingDialog

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun showLoading(show: Boolean) {
        binding.apply {
            if (show) {
                loadingView.visibility = View.VISIBLE
            } else {
                loadingView.visibility = View.GONE
            }
        }
    }
}