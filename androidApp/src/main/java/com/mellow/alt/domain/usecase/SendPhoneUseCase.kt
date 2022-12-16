package com.mellow.alt.domain.usecase

import com.mellow.alt.data.repository.net.response.user.UserExistsResponse
import com.mellow.alt.domain.repository.LoginRepository
import retrofit2.Response

class SendPhoneUseCase(private val loginRepository: LoginRepository) {
    suspend fun execute(phone: String): Response<UserExistsResponse> {
        return loginRepository.sendPhone(phone)
    }
}