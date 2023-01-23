package com.example.codelab1.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomButton (
    onclick:()->Unit,
    content:String
){

    Button(onclick,
    modifier = Modifier.padding(12.dp) , colors = ButtonDefaults.buttonColors(backgroundColor = Color(
            0xFF13BD37
        )
        ),
        shape = RoundedCornerShape(30.dp)

    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = content, color = Color.White, fontSize = 20.sp)
        }
    }

}