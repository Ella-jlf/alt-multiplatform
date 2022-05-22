package com.mellow.alt.domain.repository

import com.mellow.alt.data.repository.net.request.UserRequest
import com.mellow.alt.data.repository.net.response.user.UserExistsResponse
import com.mellow.alt.data.repository.net.response.user.UserResponse
import okhttp3.ResponseBody
import retrofit2.Response

interface LoginRepository {
    suspend fun sendPhone(phone: String): Response<UserExistsResponse>
    suspend fun sendCodeLogin(phone: String, code: String): Response<UserResponse>
    suspend fun sendCodeRegistration(phone: String, code: String): Response<UserResponse>
    suspend fun sendUserRegistration(userRequest: UserRequest): Response<ResponseBody>
}