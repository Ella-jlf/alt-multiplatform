package com.mellow.alt

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform