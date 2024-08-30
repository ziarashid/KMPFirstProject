package com.robin.kmp

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.robin.kmp.networking.RecitersClient
import com.robin.kmp.networking.createHttpClient
import io.ktor.client.engine.darwin.Darwin

fun MainViewController() = ComposeUIViewController { App( client = remember{
    RecitersClient(createHttpClient(Darwin.create()))
}) }