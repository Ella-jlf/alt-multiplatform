package com.mellow.alt.data

class RemoteRepositoryImpl(private val networkClient: NetworkClient) : RemoteRepository {
    override suspend fun loadProfile(url: String): ContentResponse<Profile> {
        return networkClient.getData(url)
    }

    override suspend fun loadProfileList(url: String): ContentResponse<List<Profile>> {
        return networkClient.getListData(url)
    }
}