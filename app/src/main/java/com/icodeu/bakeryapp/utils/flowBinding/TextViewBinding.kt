package com.icodeu.bakeryapp.utils.flowBinding

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate

@ExperimentalCoroutinesApi
fun TextView.textChanges(): InitialValueFlow<CharSequence> = callbackFlow {
    checkMainThread()
    val listener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            trySend(s)
        }

        override fun afterTextChanged(s: Editable) = Unit
    }

    addTextChangedListener(listener)
    awaitClose { removeTextChangedListener(listener) }
}
    .conflate()
    .asInitialValueFlow { text }

