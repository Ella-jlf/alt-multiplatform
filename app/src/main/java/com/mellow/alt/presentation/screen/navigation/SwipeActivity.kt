@file:Suppress("OverrideDeprecatedMigration")

package com.mellow.alt.presentation.screen.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.mellow.alt.presentation.compose.AltApp
import com.mellow.alt.presentation.compose.SwipeCard
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
            AltApp()
        }

    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}