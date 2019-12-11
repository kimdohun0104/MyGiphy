package com.dsm.mygiphy.presentation.util

import android.content.res.Configuration
import android.content.res.Resources
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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

fun RecyclerView.setStaggeredGridSpanCount() {
    (this.layoutManager as StaggeredGridLayoutManager).spanCount =
        if (Resources.getSystem().configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4
}
