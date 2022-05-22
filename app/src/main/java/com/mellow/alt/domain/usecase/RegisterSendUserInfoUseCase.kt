package com.mellow.alt.domain.usecase

import com.mellow.alt.data.repository.net.request.UserRequest
import com.mellow.alt.domain.repository.LoginRepository
import okhttp3.ResponseBody
import retrofit2.Response

class RegisterSendUserInfoUseCase(private val loginRepository: LoginRepository) {
    suspend fun execute(user: UserRequest): Response<ResponseBody> {
        return loginRepository.sendUserRegistration(user)
    }
}