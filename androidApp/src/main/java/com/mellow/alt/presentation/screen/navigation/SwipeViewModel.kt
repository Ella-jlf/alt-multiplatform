package com.mellow.alt.presentation.screen.navigation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mellow.alt.data.Profile
import com.mellow.alt.data.RemoteRepository
import com.mellow.alt.utils.SwipeCardNum
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

class SwipeViewModel @Inject constructor(private val remoteRepo: RemoteRepository) :
    ViewModel() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception with suppressed ${exception.suppressed.contentToString()}")
    }
    private val _displayAccounts = MutableLiveData<Queue<Profile>>(LinkedList<Profile>().apply {
        add(Profile(name = "1"))
        add(Profile(name = "2"))
        add(Profile(name = "3"))
        add(Profile(name = "4"))
        add(Profile(name = "5"))
        add(Profile(name = "6"))
        add(Profile(name = "7"))
        add(Profile(name = "8"))
        add(Profile(name = "9"))
        add(Profile(name = "10"))
        add(Profile(name = "11"))
    })
    val displayAccounts: LiveData<Queue<Profile>> = _displayAccounts

    private val _displayProfileFirst = MutableLiveData<Profile>()
    val displayProfileFirst: LiveData<Profile> = _displayProfileFirst

    private val _displayProfileSecond = MutableLiveData<Profile>()
    val displayProfileSecond: LiveData<Profile> = _displayProfileSecond

    private val _toggle = MutableLiveData<SwipeCardNum>(SwipeCardNum.FIRST)
    val toggle: LiveData<SwipeCardNum> = _toggle

    private val _userProfile = MutableLiveData<Profile>()
    val userProfile: LiveData<Profile> = _userProfile

    fun getProfiles() {

        CoroutineScope(Dispatchers.IO + handler).launch {
            val profiles = remoteRepo.loadProfileList("127.0.0.1").content
        }
    }

    fun sendMessage(message: String) {

    }

    fun onSwipeRight(cardNum: SwipeCardNum) {
        val profile = when (cardNum) {
            SwipeCardNum.FIRST -> {
                displayProfileFirst.value
            }
            SwipeCardNum.SECOND -> {
                displayProfileSecond.value
            }
        }

        //doSomething with that profile

        toggle()
    }

    fun onSwipeLeft(cardNum: SwipeCardNum) {
        val profile = when (cardNum) {
            SwipeCardNum.FIRST -> {
                displayProfileFirst.value
            }
            SwipeCardNum.SECOND -> {
                displayProfileSecond.value
            }
        }

        //doSomething with that profile

        toggle()
    }

    private fun toggle() {
        when (_toggle.value) {

            SwipeCardNum.FIRST -> {
                _displayProfileFirst.value = displayAccounts.value?.poll()

                _toggle.value = SwipeCardNum.SECOND
            }

            SwipeCardNum.SECOND -> {
                _displayProfileSecond.value = displayAccounts.value?.poll()

                _toggle.value = SwipeCardNum.FIRST
            }

            null -> {

            }
        }
    }

    fun checkServer() {
        CoroutineScope(Dispatchers.IO + handler).launch {

            val str = remoteRepo.checkServer("/nothing")
            Log.d("SOSI", str.content.toString())
        }
    }
}