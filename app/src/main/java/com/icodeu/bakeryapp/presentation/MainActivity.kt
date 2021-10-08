package com.icodeu.bakeryapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.icodeu.bakeryapp.R
import com.icodeu.bakeryapp.core.utils.Resource
import com.icodeu.bakeryapp.databinding.ActivityMainBinding
import com.icodeu.bakeryapp.utils.collectWhenStarted
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialog: AlertDialog
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        dialog = MaterialAlertDialogBuilder(this)
            .setView(R.layout.loading_dialog)
            .create()

        setContentView(binding.root)
        setupDialog()
    }

    private fun setupDialog() {
            mainViewModel.showDialog.collectWhenStarted(this){
                when (it) {
                    is Resource.Loading -> {
                        dialog.show()
                    }
                    is Resource.Error -> {
                        dialog.dismiss()
                    }
                    is Resource.Success -> {
                        dialog.dismiss()
                    }
                }
            }
    }
}