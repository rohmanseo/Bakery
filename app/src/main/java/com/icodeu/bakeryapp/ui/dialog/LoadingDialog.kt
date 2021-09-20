package com.icodeu.bakeryapp.ui.dialog


import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.icodeu.bakeryapp.R

class LoadingDialog : DialogFragment() {

    private lateinit var materialDialog: AlertDialog
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        materialDialog = MaterialAlertDialogBuilder(requireContext())
            .setView(R.layout.loading_dialog)
            .create()


        return materialDialog

    }
}