package com.mellow.alt.data

interface RemoteRepository {
    suspend fun loadProfile(url: String): ContentResponse<Profile>
}