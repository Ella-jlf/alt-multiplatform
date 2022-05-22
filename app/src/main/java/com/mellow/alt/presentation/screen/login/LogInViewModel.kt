package com.mellow.alt.presentation.screen.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mellow.alt.application.App
import com.mellow.alt.data.repository.net.request.UserRequest
import com.mellow.alt.data.repository.net.service.LoginService
import com.mellow.alt.domain.usecase.LoginSendCodeUseCase
import com.mellow.alt.domain.usecase.RegisterSendCodeUseCase
import com.mellow.alt.domain.usecase.RegisterSendUserInfoUseCase
import com.mellow.alt.domain.usecase.SendPhoneUseCase
import kotlinx.coroutines.*
import org.kodein.di.instance
import java.lang.Exception

class LogInViewModel : ViewModel() {
    private val loginSendCodeUseCase by App.di.instance<LoginSendCodeUseCase>()
    private val sendPhoneUseCase by App.di.instance<SendPhoneUseCase>()
    private val registerSendUserInfoUseCase by App.di.instance<RegisterSendUserInfoUseCase>()
    private val registerSendCodeUseCase by App.di.instance<RegisterSendCodeUseCase>()

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError(throwable)
    }

    val phoneNumberLiveData = MutableLiveData<String>()
    val userExistsLiveData = MutableLiveData<Boolean>()
    val codeLiveData = MutableLiveData<String>()
    val loggedIn = MutableLiveData<Boolean>(false)
    val latitudeLiveData = MutableLiveData<Float>(53.8964629f)
    val longitudeLiveData = MutableLiveData<Float>(27.5626987f)

    fun sendPhone(phone: String) {
        phoneNumberLiveData.value = phone
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val result = sendPhoneUseCase.execute(phone)
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
                val result = registerSendCodeUseCase.execute(phone, code)
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
                val result = loginSendCodeUseCase.execute(phone, code)
                if (result.isSuccessful) {
                    val user = result.body()

                    loggedIn.value = true
                }
            }
        }
    }

    fun sendUserLogin() {
        val phone = phoneNumberLiveData.value ?: return
        val lat = latitudeLiveData.value ?: return
        val lon = longitudeLiveData.value ?: return
        val user = UserRequest(
            id = null,
            name = "Alexa",
            phoneNumber = phone,
            city = "Minsk",
            workPlace = null,
            studyPlace = null,
            latitude = lat,
            longitude = lon,
            age = 18
        )

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val result = registerSendUserInfoUseCase.execute(user)
            if (result.isSuccessful) {
                val user = result.body()

                loggedIn.value = true
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