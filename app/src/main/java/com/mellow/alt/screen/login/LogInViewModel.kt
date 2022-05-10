package com.mellow.alt.screen.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mellow.alt.application.App
import com.mellow.alt.net.service.UserService
import kotlinx.coroutines.*
import org.kodein.di.instance
import java.lang.Exception

class LogInViewModel : ViewModel() {
    private val userService by App.di.instance<UserService>()

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError(throwable)
    }

    val phoneNumberLiveData = MutableLiveData<String>()
    val userExistsLiveData = MutableLiveData<Boolean>()
    val codeLiveData = MutableLiveData<String>()
    val loggedIn = MutableLiveData<Boolean>(false)

    fun sendPhone(phone: String) {
        phoneNumberLiveData.value = phone
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val result = userService.sendPhone(phone)
            if (result.isSuccessful) {
                val userExists = result.body()?.userExists ?: throw Exception("Null response")

                userExistsLiveData.value = userExists
            }
        }
    }

    fun sendCodeRegister(code: String) {
        codeLiveData.value = code
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val phone = phoneNumberLiveData.value ?: throw Exception("Null phone number")

            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val result = userService.sendCodeRegister(phone, code)
                if (result.isSuccessful) {
                    val user = result.body()

                    loggedIn.value = true
                }
            }
        }
    }


    fun sendCodeLogin(code: String) {
        codeLiveData.value = code
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val phone = phoneNumberLiveData.value ?: throw Exception("Null phone number")

            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val result = userService.sendCodeLogin(phone, code)
                if (result.isSuccessful) {
                    val user = result.body()

                    loggedIn.value = true
                }
            }
        }
    }


    private fun onError(throwable: Throwable) {
        Log.e("LogIn", "${throwable.message}")
    }

    override fun onCleared() {
        super.onCleared()

        job?.cancel()
    }
}