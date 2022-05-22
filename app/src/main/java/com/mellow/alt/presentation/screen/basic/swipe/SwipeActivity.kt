package com.mellow.alt.presentation.screen.basic.swipe

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mellow.alt.R
import com.mellow.alt.databinding.ActivitySwipeBinding
import com.mellow.alt.presentation.screen.basic.swipe.adapter.SwipeAdapter

class SwipeActivity : AppCompatActivity(R.layout.activity_swipe) {

    companion object {
        fun getIntent(context: Context) = Intent(context, SwipeActivity::class.java)
    }

    private val binding by viewBinding(ActivitySwipeBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
    }

    private fun initAdapter() {
        binding.vSwipes.apply {
            adapter = SwipeAdapter()
            layoutManager = object : LinearLayoutManager(context) {
                override fun canScrollVertically(): Boolean {
                    return false
                }

                override fun canScrollHorizontally(): Boolean {
                    return false
                }
            }
        }
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}