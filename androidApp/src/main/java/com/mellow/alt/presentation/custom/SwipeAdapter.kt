package com.mellow.alt.presentation.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.LinearLayout
import java.util.*

class SwipeAdapter @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attributeSet, defStyle) {

    var maxItems = 12
    var itemsLeftToStart = 3

    private var itemsLeft = 0
    private val generatedViews: Queue<SwipeView> = LinkedList()

    var items = listOf<Any>()
        set(value) {
            field = value
            itemsLeft = value.size
            fill()
        }

    private fun fill() {
        generatedViews.peek()

        val refillCount = if (items.size > maxItems)
            maxItems else items.size

        for (i in 0 until refillCount) {
            val view = SwipeView(context).apply {
                this.onViewTossedOut = ::downItemsLeft
            }
            generatedViews.add(view)
        }
    }

    private fun refill() {

    }

    private fun downItemsLeft() {

    }

}