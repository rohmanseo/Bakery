package com.icodeu.bakeryapp

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.icodeu.bakeryapp.databinding.ActivityMainBinding
import com.icodeu.bakeryapp.ui.dialog.LoadingDialog
import com.icodeu.bakeryapp.ui.dialog.LoadingDialog.Companion.DIALOG_LOADING_TAG

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialog: LoadingDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        dialog = LoadingDialog.getInstance()
        setContentView(binding.root)
    }

    fun showLoading(show: Boolean) {
        binding.apply {
            if (show) {
                dialog.show(supportFragmentManager, DIALOG_LOADING_TAG)
            } else {
                dialog.dismiss()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

    }

}