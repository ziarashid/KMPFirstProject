package com.robin.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform