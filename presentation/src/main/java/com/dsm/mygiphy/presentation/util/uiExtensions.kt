package com.dsm.mygiphy.presentation.util

import android.view.View
import android.widget.EditText
import com.dsm.mygiphy.R
import com.google.android.material.snackbar.Snackbar

fun retrySnackbar(view: View, task: () -> Unit) =
    Snackbar.make(view, R.string.fail_internal, Snackbar.LENGTH_LONG)
        .setAction(R.string.retry) { task.invoke() }.show()

fun EditText.setEditorActionListener(action: Int, callback: () -> Unit) =
    this.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == action) {
            callback()
            return@setOnEditorActionListener true
        }
        false
    }