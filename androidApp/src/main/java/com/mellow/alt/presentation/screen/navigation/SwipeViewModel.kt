package com.mellow.alt.presentation.screen.navigation

import androidx.lifecycle.ViewModel
import com.mellow.alt.data.NetworkClient
import com.mellow.alt.data.RemoteRepository
import com.mellow.alt.data.RemoteRepositoryImpl
import com.mellow.alt.domain.usecase.LoginSendCodeUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SwipeViewModel @Inject constructor(private val loginSendCodeUseCase: LoginSendCodeUseCase) :
    ViewModel() {
    private val remoteRepo: RemoteRepository = RemoteRepositoryImpl(NetworkClient())
    fun getProfiles() {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler got $exception with suppressed ${exception.suppressed.contentToString()}")
        }
        CoroutineScope(Dispatchers.IO + handler).launch {
            val profiles = remoteRepo.loadProfileList("127.0.0.1").content
        }
    }
}