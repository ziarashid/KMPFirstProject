package com.robin.kmp.customIcons

 import androidx.compose.foundation.background
 import androidx.compose.foundation.border
 import androidx.compose.foundation.layout.*
 import androidx.compose.foundation.shape.CircleShape
 import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
 import androidx.compose.ui.draw.shadow
 import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
 import androidx.compose.ui.text.style.TextAlign
 import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

 @Composable
fun CustomIcon(text: String="01") {
     Box(
         modifier = Modifier
             .size(44.dp) // Adjust the size as needed
             .background(Color(0xffffffff), shape = CircleShape)
             // .shadow(8.dp, shape = CircleShape) // Apply elevation here
             .border(4.dp, Color(0xffCD0000), shape = CircleShape)
             .wrapContentSize(Alignment.Center) // Centers content inside the box
     ) {
         Text(
             text = text,
             color = Color.Black,
             style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
             textAlign = TextAlign.Center,
             modifier = Modifier.align(Alignment.Center) // Centers text inside the box
         )
     }
}