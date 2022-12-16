package com.mellow.alt.data.repository.net.request

import com.google.gson.annotations.SerializedName

class UserRequest(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("first_name")
    val name: String,
    @SerializedName("phone")
    val phoneNumber: String,
    @SerializedName("city_name")
    val city: String?,
    @SerializedName("workplace")
    val workPlace: String?,
    @SerializedName("study_place")
    val studyPlace: String?,
    @SerializedName("lat")
    val latitude: Float,
    @SerializedName("lon")
    val longitude: Float,
    @SerializedName("age")
    val age: Int
)