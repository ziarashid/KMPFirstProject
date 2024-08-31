package com.robin.kmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.robin.kmp.customIcons.CustomIcon
import com.robin.kmp.model.Reciter
import com.robin.kmp.networking.RecitersClient
import com.robin.kmp.screens.DetailScreen
import com.robin.kmp.screens.QuranReciters
import com.robin.kmp.util.NetworkError
import com.robin.kmp.util.onError
import com.robin.kmp.util.onSuccess
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmpfirstproject.composeapp.generated.resources.Res
import kmpfirstproject.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.launch

@Composable
@Preview
fun App(client: RecitersClient) {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "reciters") {
            composable("reciters") {
                var isLoading by remember { mutableStateOf(false) }
                var reciters by remember { mutableStateOf(emptyList<Reciter>()) }
                var errorMessage by remember { mutableStateOf<NetworkError?>(null) }
                val scope = rememberCoroutineScope()
                Box(modifier = Modifier.fillMaxSize().background(color = Color(0xffFFFFFF)), contentAlignment = Alignment.Center) {
                    LaunchedEffect(Unit) {
                        scope.launch {
                            isLoading = true
                            errorMessage = null
                            client.getReciters()
                                .onSuccess {
                                    reciters = it.reciters
                                }
                                .onError { error ->
                                    errorMessage = error
                                }
                            isLoading = false

                        }
                    }
                    if (isLoading) {
                        CircularProgressIndicator()

                    } else {
                        if (errorMessage != null) {
                            Text(errorMessage!!.name)
                        } else {
                            QuranReciters( reciters){
                                reciter ->
                                navController.navigate("detail/${reciter.name}/${urlEncode(reciter.Server)}/${reciter.suras}")
                            }
                        }

                    }
                }

            }

            composable("detail/{name}/{server}/{surahs}") {
            val name = it.arguments?.getString("name") ?: ""
            val server = it.arguments?.getString("server") ?: ""
            val surahs = it.arguments?.getString("surahs") ?: ""
                DetailScreen(name,server,surahs) {
                    navController.navigateUp()
                }
            }
        }



    }
}
fun urlEncode(url: String): String = url
    .replace(" ", "%20")
    .replace("!", "%21")
    .replace("#", "%23")
    .replace("$", "%24")
    .replace("&", "%26")
    .replace("'", "%27")
    .replace("(", "%28")
    .replace(")", "%29")
    .replace("*", "%2A")
    .replace("+", "%2B")
    .replace(",", "%2C")
    .replace("/", "%2F")
    .replace(":", "%3A")
    .replace(";", "%3B")
    .replace("=", "%3D")
    .replace("?", "%3F")
    .replace("@", "%40")
    .replace("[", "%5B")
    .replace("]", "%5D")