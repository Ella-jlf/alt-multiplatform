package com.mellow.alt.presentation.screen.basic.swipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mellow.alt.R

class SwipeAdapter : RecyclerView.Adapter<SwipeViewHolder>() {

    companion object {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeViewHolder {
        return SwipeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_swipe, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SwipeViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 12
    }
}