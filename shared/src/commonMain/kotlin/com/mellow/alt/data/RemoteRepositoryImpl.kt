package com.mellow.alt.data

import io.ktor.client.utils.*

class RemoteRepositoryImpl(private val networkClient: NetworkClient) : RemoteRepository {
    override suspend fun sendMessage(
        url: String,
        message: String,
        receiver: Int
    ): ContentResponse<EmptyContent> {
        val data = message + receiver
        return networkClient.postData(url, data)
    }

    override suspend fun checkServer(url: String): ContentResponse<Nothing> {
        return networkClient.getData(url)
    }

    override suspend fun loadProfile(url: String): ContentResponse<Profile> {
        return networkClient.getData(url)
    }

    override suspend fun loadProfileList(url: String): ContentResponse<List<Profile>> {
        return networkClient.getListData(url)
    }
}