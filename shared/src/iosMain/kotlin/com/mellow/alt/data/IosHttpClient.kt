package com.mellow.alt.data

import io.ktor.client.*
import io.ktor.client.engine.ios.*
import io.ktor.client.features.logging.*

internal fun IosHttpClient() = HttpClient(Ios) {
    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }
}

actual fun createHttpClient(): HttpClient {
    return IosHttpClient()
}

actual val baseUrl: String
    get() = "127.0.0.1"