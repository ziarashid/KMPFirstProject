package com.robin.kmp.screens

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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.robin.kmp.customIcons.CustomIcon

@Composable
fun DetailScreen(name: String, navigateUp: () -> Unit) {

    Scaffold (topBar = {
        TopAppBar(
            elevation = 8.dp,
            modifier = Modifier.background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xffCD0000), Color(0xffE70000), Color(0xffCD0000))
                )
            ),
            backgroundColor = Color(0xffCD0000),
            title = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(name, fontSize = 18.sp,color = Color.White)
                }
            },
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, tint = Color.White, contentDescription = "Go Back")
                }
            }
        )
    }){
        Box(modifier = Modifier.fillMaxSize().background(color = Color(0xffFFFFFF)).padding(10.dp), contentAlignment = Alignment.Center) {

                Text("$name Clicked", fontSize = 18.sp, color = Color.Black)




        }
    }

}