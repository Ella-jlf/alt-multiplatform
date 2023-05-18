package com.mellow.alt.utils

import android.content.res.Resources
import android.util.TypedValue


fun Number.dpToPx(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )
}