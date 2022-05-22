package com.mellow.alt.data.repository.net.service

import com.mellow.alt.data.repository.net.request.UserRequest
import com.mellow.alt.data.repository.net.response.user.UserExistsResponse
import com.mellow.alt.data.repository.net.response.user.UserResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface LoginService {

    @FormUrlEncoded
    @POST("/phone/")
    suspend fun sendPhone(
        @Field("phone") phone: String
    ): Response<UserExistsResponse>

    @FormUrlEncoded
    @POST("/register/code")
    suspend fun sendCodeRegister(
        @Field("phone") phone: String,
        @Field("code") code: String
    ): Response<UserResponse>

    @FormUrlEncoded
    @POST("/code_login")
    suspend fun sendCodeLogin(
        @Field("phone") phone: String,
        @Field("code") code: String
    ): Response<UserResponse>

    @FormUrlEncoded
    @POST("/preregister")
    suspend fun sendUserRegistration(@Body userRequest: UserRequest): Response<ResponseBody>
}