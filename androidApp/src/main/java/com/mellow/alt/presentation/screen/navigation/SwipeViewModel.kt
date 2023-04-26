package com.mellow.alt.presentation.screen.navigation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mellow.alt.data.NetworkClient
import com.mellow.alt.data.RemoteRepository
import com.mellow.alt.data.RemoteRepositoryImpl
import com.mellow.alt.domain.usecase.LoginSendCodeUseCase
import kotlinx.coroutines.*
import javax.inject.Inject

class SwipeViewModel @Inject constructor(private val remoteRepo: RemoteRepository) :
    ViewModel() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception with suppressed ${exception.suppressed.contentToString()}")
    }

    fun getProfiles() {

        CoroutineScope(Dispatchers.IO + handler).launch {
            val profiles = remoteRepo.loadProfileList("127.0.0.1").content
        }
    }

    fun sendMessage(message: String) {

    }

    fun checkServer() {
        CoroutineScope(Dispatchers.IO + handler).launch {

            val str = remoteRepo.checkServer("/nothing")
            Log.d("SOSI", str.content.toString())
        }
    }
}