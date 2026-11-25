package com.example.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.pm.ShortcutInfoCompat
import com.example.calculadora.ui.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    calculadoraHome(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun calculadoraHome(modifier: Modifier = Modifier) {
    //Declaramos las variable que serán mutables
    var pantalla by remember { mutableStateOf("0") }

    var primerNumero by remember { mutableStateOf("") }
    var operador by remember { mutableStateOf("") }
    //Vericamos que el numeor se introduzca cuando pantalla es 0
    //si no lo es e oncantena
    val agregarNumero = { numero: String ->
        if (pantalla == "0") {
            pantalla = numero
        } else {
            pantalla += numero
        }
    }
    //asignamos el operaador
    val agregarOperador = { op: String ->
        primerNumero = pantalla
        pantalla = "0"
        operador = op
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .padding(16.dp),
            color = androidx.compose.ui.graphics.Color.LightGray,
            shadowElevation = 4.dp
        ) {
            Text(
                pantalla,
                modifier = Modifier.padding(16.dp)

            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier

                .fillMaxSize()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(5.dp)

        ) {
            Button(
                onClick = { agregarNumero("7") },
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(text = "7")
            }
            Button(
                onClick = { agregarNumero("8") },
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(text = "8")
            }
            Button(
                onClick = { agregarNumero("9") },
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(text = "9")
            }
            Button(
                onClick = { agregarOperador("/") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF64308),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(text = "/")

            }
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Button(
                onClick = { agregarNumero("4") },
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(text = "4")
            }
            Button(
                onClick = { agregarNumero("5") },
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(text = "5")
            }
            Button(
                onClick = { agregarNumero("6") },
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(text = "6")
            }
            Button(
                onClick = { agregarOperador("*") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF64308),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(text = "*")

            }
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Button(
                onClick = { agregarNumero("1") },
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(text = "1")
            }
            Button(
                onClick = { agregarNumero("2") },
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(text = "2")
            }
            Button(
                onClick = { agregarNumero("3") },
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(text = "3")
            }
            Button(
                onClick = { agregarOperador("-") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF64308),
                    contentColor = Color.White
                ),

                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(text = "-")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(5.dp)

        ) {

            Button(
                onClick = { agregarNumero("0") },

                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(text = "0")
            }
            Button(
                onClick = {
                    pantalla = "0"
                    primerNumero = ""
                    operador = ""
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF64308),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(text = "C")
            }
            Button(
                onClick = {
                    //Aqui añadiremos el resultado al la pantalla
                    if (primerNumero.isNotEmpty() && operador.isNotEmpty()) {
                        val num1 = primerNumero.toDoubleOrNull() ?: 0.0
                        val num2 = pantalla.toDoubleOrNull() ?: 0.0
                        val resultado = when (operador) {
                            "+" -> num1 + num2
                            "-" -> num1 - num2
                            "*" -> num1 * num2
                            "/" -> if(num2 != 0.0) num1 / num2 else 0.0
                            else -> 0.0
                        }
                        //Mostramos el resultado en la pantalla en string
                        pantalla = resultado.toString()
                        primerNumero = ""
                        operador = ""
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF64308),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(text = "=")
            }
            Button(
                onClick = { agregarOperador("+") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF64308),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(text = "+")
            }

        }


    }

}

@Preview(showBackground = true)
@Composable
fun CalculadoraPreview() {
    CalculadoraTheme {
        calculadoraHome()
    }

}