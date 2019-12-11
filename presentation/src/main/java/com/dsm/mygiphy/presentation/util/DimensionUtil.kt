package com.dsm.mygiphy.presentation.util

import android.content.res.Configuration
import android.content.res.Resources

object DimensionUtil {

    fun getHeightByRatioWithSpan(itemHeight: Int) =
        (itemHeight * getHeightIncrease()).toInt()

    private fun getHeightIncrease() =
        Resources.getSystem().displayMetrics.widthPixels / getSpanCountWithOrientation() / 200.0

    private fun getSpanCountWithOrientation() =
        if (Resources.getSystem().configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4
}