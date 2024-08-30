package com.robin.kmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.robin.kmp.networking.RecitersClient
import com.robin.kmp.networking.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(
               client = remember{
                     RecitersClient(createHttpClient(OkHttp.create()))
               }
                 )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(
        client = remember{
            RecitersClient(createHttpClient(OkHttp.create()))
        }
    )
}