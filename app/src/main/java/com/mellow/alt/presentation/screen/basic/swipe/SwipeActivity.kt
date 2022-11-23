@file:Suppress("OverrideDeprecatedMigration")

package com.mellow.alt.presentation.screen.basic.swipe

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mellow.alt.R
import com.mellow.alt.databinding.ActivitySwipeBinding
import com.mellow.alt.presentation.screen.basic.swipe.adapter.SwipeAdapter
import com.mellow.alt.presentation.screen.login.LogInViewModel
import com.mellow.alt.utils.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

@Suppress("OverrideDeprecatedMigration")
class SwipeActivity : DaggerAppCompatActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, SwipeActivity::class.java)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SwipeViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel

        setContent {
            Text(text = "Sosi Huy")
        }

    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}