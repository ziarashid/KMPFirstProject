package com.robin.kmp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.robin.kmp.customIcons.CustomIcon
import com.robin.kmp.model.Reciter
import com.robin.kmp.model.Reciters
import com.robin.kmp.util.NetworkError
import com.robin.kmp.util.onError
import com.robin.kmp.util.onSuccess
import kotlinx.coroutines.launch

@Composable
fun QuranReciters(reciters: List<Reciter>,onItemClick: (Reciter) -> Unit) {

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
                    Text("Quran Reciters", fontSize = 18.sp,color = Color.White)
                }
            }
        )
    }){
        Box(modifier = Modifier.fillMaxSize().background(color = Color(0xffFFFFFF)), contentAlignment = Alignment.Center) {
                    if(reciters != null&&reciters.size>0){
                        Column(
                            modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
                        ) {
                            reciters.forEach {
                                Row(
                                    Modifier.fillMaxWidth().padding(10.dp).clickable {
                                        onItemClick(it)
                                    },
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Box (modifier = Modifier.shadow(4.dp, shape = CircleShape)) {
                                        CustomIcon(text = (reciters.indexOf(it) + 1).toString())
                                    }
                                    Spacer(modifier = Modifier.width(0.dp))
                                    Column(
                                        modifier = Modifier.weight(1f).padding(start = 10.dp)
                                    ) {
                                        Text(it.name, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.align(
                                            Alignment.Start))
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(it.rewaya, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.align(
                                            Alignment.Start))
                                    }
                                    Icon(
                                        imageVector = Icons.Default.ArrowForward,
                                        contentDescription = "Next",
                                        tint = Color(0xffCD0000),  // You can customize the color here
                                        modifier = Modifier.size(24.dp)  // You can customize the size here
                                    )
                                }
                            }

                        }
                    }else{
                        Text("No Data Found", fontSize = 18.sp, color = Color.Black)
                    }



            }
        }

}