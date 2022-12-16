package com.mellow.alt.data.repository.net

import com.mellow.alt.data.repository.net.service.LoginService
import com.mellow.alt.domain.repository.LoginRepository
import com.mellow.alt.data.repository.net.request.UserRequest
import com.mellow.alt.data.repository.net.response.user.UserExistsResponse
import com.mellow.alt.data.repository.net.response.user.UserResponse
import okhttp3.ResponseBody
import retrofit2.Response

class LoginRepositoryImpl(private val loginService: LoginService) : LoginRepository {
    override suspend fun sendPhone(phone: String): Response<UserExistsResponse> {
        return loginService.sendPhone(phone)
    }

    override suspend fun sendCodeLogin(phone: String, code: String): Response<UserResponse> {
        return loginService.sendCodeLogin(phone, code)
    }

    override suspend fun sendCodeRegistration(phone: String, code: String): Response<UserResponse> {
        return loginService.sendCodeRegister(phone, code)
    }

    override suspend fun sendUserRegistration(userRequest: UserRequest): Response<ResponseBody> {
        return loginService.sendUserRegistration(userRequest)
    }
}