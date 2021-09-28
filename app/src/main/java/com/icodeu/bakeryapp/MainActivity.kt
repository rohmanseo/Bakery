package com.icodeu.bakeryapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.icodeu.bakeryapp.databinding.ActivityMainBinding
import com.icodeu.bakeryapp.utils.getScreenWidthSize

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        dialog = MaterialAlertDialogBuilder(this)
            .setView(R.layout.loading_dialog)
            .create()
        Log.d("screen width", this.getScreenWidthSize().toString())
        setContentView(binding.root)
    }

    fun showLoading(show: Boolean) {
        binding.apply {
            if (show) {
                dialog.show()
            } else {
                dialog.dismiss()
            }
        }
    }


}