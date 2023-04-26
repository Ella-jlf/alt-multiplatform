package com.mellow.alt.data

import io.ktor.client.utils.*

interface RemoteRepository {
    suspend fun loadProfile(url: String): ContentResponse<Profile>
    suspend fun loadProfileList(url: String): ContentResponse<List<Profile>>
    suspend fun sendMessage(
        url: String,
        message: String,
        receiver: Int
    ): ContentResponse<EmptyContent>

    suspend fun checkServer(url: String): ContentResponse<Nothing>
}