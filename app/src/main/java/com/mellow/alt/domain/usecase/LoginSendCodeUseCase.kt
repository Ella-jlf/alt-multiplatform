package com.mellow.alt.domain.usecase

import com.mellow.alt.data.repository.net.response.user.UserExistsResponse
import com.mellow.alt.data.repository.net.response.user.UserResponse
import com.mellow.alt.domain.repository.LoginRepository
import retrofit2.Response

class LoginSendCodeUseCase(private val loginRepository: LoginRepository) {
    suspend fun execute(phone: String, code: String): Response<UserResponse> {
        return loginRepository.sendCodeLogin(phone, code)
    }
}