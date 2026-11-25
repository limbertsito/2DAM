package com.example.practicaexamenjetpacklanzar_aleatorio

import androidx.compose.ui.graphics.Color

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.practicaexamenjetpacklanzar_aleatorio.ui.theme.PracticaExamenJetpackLANZAR_ALEATORIOTheme
import com.example.practicaexamenjetpacklanzar_aleatorio.ui.theme.naranja
import com.example.practicaexamenjetpacklanzar_aleatorio.ui.theme.red
import com.example.practicaexamenjetpacklanzar_aleatorio.ui.theme.verde

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaExamenJetpackLANZAR_ALEATORIOTheme {
                LanzarNumero()
            }
        }
    }
}

@Composable
fun LanzarNumero(){

    Interfaz(modifier= Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center),
        )
}

@Composable
fun Interfaz(modifier: Modifier) {
    var numero by remember { mutableStateOf((10..20).random()) }
    var colorR = when(numero){
        in 0..3 -> red
        in 4 ..10 ->  naranja
        in 11..20 ->verde
        else -> Color.Black
    }
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.Center
    ) {
        Text("$numero",
            fontSize = 50.sp,
            color= colorR
            )
        Button(
            onClick = {

                numero--
            },
            enabled = numero!=0

        ){
            Text(stringResource(R.string.decrement))
        }
    }
}