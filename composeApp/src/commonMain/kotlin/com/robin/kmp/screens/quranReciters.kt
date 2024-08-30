package com.robin.kmp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.robin.kmp.customIcons.CustomIcon
import com.robin.kmp.model.Reciter
import com.robin.kmp.model.Reciters

@Composable
fun QuranReciters(reciters: List<Reciter>) {
    Column(
        modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
    ) {
        reciters.forEach {
            Row(
                Modifier.fillMaxWidth().padding(10.dp),
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
}