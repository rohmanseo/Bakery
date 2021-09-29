package com.icodeu.bakeryapp.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.icodeu.bakeryapp.databinding.DialogProfileBinding

class ProfileDialog : DialogFragment() {

    private lateinit var binding: DialogProfileBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogProfileBinding.inflate(layoutInflater)
        binding.apply {
            btnClose.setOnClickListener {
                this@ProfileDialog.dismiss()
            }

        }

        return MaterialAlertDialogBuilder(requireContext())
            .setView(binding.root)
            .create()

    }



}