package com.mellow.alt.net.response.user

import com.google.gson.annotations.SerializedName

class UserResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("first_name")
    val name: String?,
    @SerializedName("phone")
    val phoneNumber: String?,
    @SerializedName("city_name")
    val city: String?,
    @SerializedName("photo_list")
    val imageList: List<String>?,
    @SerializedName("workplace")
    val workPlace: String?,
    @SerializedName("study_place")
    val studyPlace: String?,
    @SerializedName("lat")
    val latitude: Float?,
    @SerializedName("lon")
    val longitude: Float?,
    @SerializedName("age")
    val age: Int?
)

class UserExistsResponse(
    @SerializedName("user_exists")
    val userExists: Boolean?
)