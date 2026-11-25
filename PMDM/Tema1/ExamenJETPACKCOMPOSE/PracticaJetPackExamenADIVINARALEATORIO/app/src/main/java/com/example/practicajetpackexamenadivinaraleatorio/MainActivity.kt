package com.example.practicajetpackexamenadivinaraleatorio

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicajetpackexamenadivinaraleatorio.ui.theme.PracticaJetPackExamenADIVINARALEATORIOTheme
import org.w3c.dom.Text

const val LIMIT = 5
const val MIN = 1
const val MAX = 10
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaJetPackExamenADIVINARALEATORIOTheme {
              Scaffold(
                  modifier = Modifier.fillMaxSize()
                      .padding()
              ) {
                  innerPadding->
                  HomeAdivina(modifier = Modifier.fillMaxSize()
                      .padding(innerPadding))
              }
            }
        }
    }
}

@Composable
fun HomeAdivina(modifier: Modifier){

    EstructuraJuego(
        modifier= Modifier.fillMaxSize()
            .padding(24.dp)
    )
}

@Composable
fun EstructuraJuego(modifier: Modifier) {
 var numeroBuscado by remember { mutableStateOf((1..10).random()) }
    var contador by remember { mutableStateOf(LIMIT) }
    var acertado by remember { mutableStateOf(false) }
    var numero by remember { mutableStateOf((1..10).random()) }
    var fraseResultado by remember { mutableStateOf("Selecciona un numero y comprueba") }
    var color by remember { mutableStateOf(Color.Black) }
        Column(
            modifier= Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(
                space= 15.dp,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Text(
                "ADIVINA EL NÚMERO",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
                )
            Text("Número entre $MIN y $MAX")

            Row(
                modifier= Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 10.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        numero--
                    }, enabled = numero>=MIN && !acertado,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                )
                {
                    Text("-")
                }
                Text(
                    "$numero",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = {
                        numero++
                    },
                    enabled = numero<MAX && !acertado,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue
                    )
                ) {
                    Text("+")
                }


            }
            Text("Intentos restantes : $contador")
            Button(
                onClick = {
                    contador--
                    if (numeroBuscado==numero){
                        acertado = true
                        fraseResultado= "!Enhorabuena has acertado¡"
                        color=Color.Green
                    }
                    else if(numero < numeroBuscado){
                        fraseResultado="El numero es MAYOR"
                    }
                    else{
                        fraseResultado="El número es MENOR"
                    }



                },
                enabled = (!acertado && contador>0)
            ) {
                Text("Comprobar")
            }
            Text(fraseResultado,
                color = color)
            Button(
                onClick = {
                    numeroBuscado = (1..10).random()
                    numero=(1..10).random()
                    fraseResultado = "Selecciona un numero y comprueba"
                    contador=LIMIT
                    acertado=false
                    color=Color.Black
                }
            ) {
                Text("Nueva Partida")
            }
        }

}
