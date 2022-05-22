package com.mellow.alt.presentation.screen.basic.swipe

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mellow.alt.R
import com.mellow.alt.databinding.ActivitySwipeBinding

class SwipeActivity : AppCompatActivity(R.layout.activity_swipe) {

    companion object {
        fun getIntent(context: Context) = Intent(context, SwipeActivity::class.java)
    }

    private val binding by viewBinding(ActivitySwipeBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}