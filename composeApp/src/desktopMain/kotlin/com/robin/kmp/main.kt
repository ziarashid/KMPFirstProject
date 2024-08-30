package com.robin.kmp

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.robin.kmp.networking.RecitersClient
import com.robin.kmp.networking.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMPFirstProject",
    ) {
        App(
            client = remember{
                RecitersClient(createHttpClient(OkHttp.create()))
            }
        )
    }
}