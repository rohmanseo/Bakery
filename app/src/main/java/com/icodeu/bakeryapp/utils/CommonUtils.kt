package com.icodeu.bakeryapp.utils

import android.util.Patterns
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.icodeu.bakeryapp.utils.Constant.BASE_URL

object CommonUtils {
    fun String.isValidEmail(): Boolean {
        return !Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    fun isNotError(vararg textInputLayouts: TextInputLayout): Boolean {
        textInputLayouts.forEach {
            if (it.error != null) {
                return false
            }
        }
        return true
    }
    fun isNotEmpty(vararg textInputEditTexts: TextInputEditText): Boolean {
        textInputEditTexts.forEach {
            if (it.text.isNullOrEmpty()) {
                return false
            }
        }
        return true
    }

    fun View.shortSnackbar(message: String) {
        Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
    }

    fun isConnected(): Boolean {
        val command = "ping -c 1 $BASE_URL"
        return Runtime.getRuntime().exec(command).waitFor() == 0
    }

}