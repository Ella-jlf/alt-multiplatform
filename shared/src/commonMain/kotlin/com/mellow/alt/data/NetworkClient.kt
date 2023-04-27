package com.mellow.alt.data

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

expect fun createHttpClient(): HttpClient
expect val baseUrl: String

class NetworkClient {
    val httpClient = createHttpClient()


    suspend inline fun <reified T> getData(path: String): ContentResponse<T> {
        val contentResponse = ContentResponse<T>()

        try {

            val json = httpClient.get<String> {
                url {
                    protocol = URLProtocol.HTTP
                    host = baseUrl
                    path(path)
                    port = 4000
                }
            }
            val jsonDecoder = Json { ignoreUnknownKeys = true }
            val result = jsonDecoder.decodeFromString<T>(json)

            contentResponse.content = result
        } catch (ex: Exception) {
            val error = ErrorResponse(ex.message.toString())
            contentResponse.errorResponse = error
        }
        return contentResponse
    }


    suspend inline fun <reified T> getListData(path: String): ContentResponse<List<T>> {
        val contentResponse = ContentResponse<List<T>>()

        try {
            val json = httpClient.get<String> {
                url {
                    protocol = URLProtocol.HTTPS
                    host = baseUrl
                    path(path)
                    port = 4000
                    //header("X-Api-Key", NetworkConfig.shared.apiKey)
                }
            }
            val jsonDecoder = Json { ignoreUnknownKeys = true }
            val result = jsonDecoder.decodeFromString<List<T>>(json)
            contentResponse.content = result
        } catch (ex: Exception) {
            val error = ErrorResponse(ex.message.toString())
            contentResponse.errorResponse = error
        }
        return contentResponse
    }

    suspend inline fun <reified T> postData(
        path: String,
        data: String,
    ): ContentResponse<T> {
        val contentResponse = ContentResponse<T>()
        try {


            val json = httpClient.post<String> {
                url {
                    protocol = URLProtocol.HTTP
                    host = baseUrl
                    path(path)
                    port = 4000
                    body = data
                }

            }
            val jsonDecoder = Json { ignoreUnknownKeys = true }

            contentResponse.content = jsonDecoder.decodeFromString<T>(json)
        } catch (ex: Exception) {
            val error = ErrorResponse(ex.message.toString())
            contentResponse.errorResponse = error
        }
        return contentResponse
    }
}