package com.example.adivinarnumeroaleatorio

import android.R.attr.fontWeight
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adivinarnumeroaleatorio.ui.theme.AdivinarNumeroAleatorioTheme

const val MIN_VALUE = 1
const val MAX_VALUE = 10
const val MAX_INTENTOS = 5

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdivinarNumeroAleatorioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var numAleatorio by remember { mutableStateOf((1..10).random()) }
    var contador by remember { mutableStateOf(0) }
    var intentosRestantes by remember { mutableStateOf(MAX_INTENTOS) }
    var mensaje by remember { mutableStateOf("Selecciona un número y comprueba") }
    var colorMensaje by remember { mutableStateOf(Color.Black) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = "ADIVINA EL NUMERO",
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Número entre $MIN_VALUE y $MAX_VALUE",
            modifier = Modifier.padding(bottom = 16.dp),
            fontSize = 10.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Button(
                onClick = {
                    contador--
                },
                enabled = contador >MIN_VALUE && intentosRestantes>0 && colorMensaje!= Color.Green,
                modifier = Modifier.padding(15.dp)


            ) {
                Text(text = "-")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "$contador",
                modifier = Modifier.padding(5.dp),
                fontSize = 40.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.width(5.dp))
            Button(

                onClick = {
                    contador++


                },
                enabled = contador < MAX_VALUE && intentosRestantes >0 && colorMensaje!= Color.Green,
                modifier = Modifier.padding(15.dp)
            ) {
                Text(text = "+")
            }

        }
        Text(
            text = "Intentos restantes: $intentosRestantes",
            modifier = Modifier.padding(bottom = 16.dp),
            fontSize = 10.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )
        Button(
            onClick = {
                intentosRestantes--
                if(contador == numAleatorio){
                    mensaje="!ENHOIRABUENA¡ Has acertado el número $numAleatorio en $intentosRestantes intentos"
                    colorMensaje= Color.Green
                }else if(contador > numAleatorio){
                    mensaje="El número secreto es MENOR"
                    colorMensaje= Color.Gray
                }else {
                    mensaje="El número secreto es MAYOR"
                    colorMensaje= Color.Gray

                }
            },
            enabled = intentosRestantes==0 || colorMensaje!= Color.Green,
            modifier = Modifier.padding(16.dp)

        ) {
            Text(
                text = "Comprobar"
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = mensaje,
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign =  androidx.compose.ui.text.style.TextAlign.Center,
                color = colorMensaje,

            )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                //Reiniciamso el juego
                mensaje="Selecciona un número y comprueba"
                contador=0
                intentosRestantes=MAX_INTENTOS
                numAleatorio=(1..10).random()
                colorMensaje= Color.Black
            }
        ) {
            Text(
                text = "Nueva Partida"
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}