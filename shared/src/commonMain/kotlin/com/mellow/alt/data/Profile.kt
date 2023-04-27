package com.mellow.alt.data

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Profile(
    //@SerializedName("id")
    val id: Int? = null,
    //@SerializedName("first_name")
    val name: String? = null,
    //@SerializedName("phone")
    val phoneNumber: String? = null,
    //@SerializedName("city_name")
    val city: String? = null,
    //@SerializedName("photo_list")
    val imageList: List<String>? = null,
    //@SerializedName("workplace")
    val workPlace: String? = null,
    //@SerializedName("study_place")
    val studyPlace: String? = null,
    //@SerializedName("lat")
    val latitude: Float? = null,
    //@SerializedName("lon")
    val longitude: Float? = null,
    //@SerializedName("age")
    val age: Int? = null,
)

@kotlinx.serialization.Serializable
data class Nothing(
    @SerialName("bla")
    val hyi: String?
)
