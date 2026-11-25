package com.example.practicajetpackexamenbase_imponible

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.practicajetpackexamenbase_imponible.ui.theme.PracticaJetpackExamenBASE_IMPONIBLETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaJetpackExamenBASE_IMPONIBLETheme {
                HomeBaseImponible(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
@Composable
fun HomeBaseImponible(modifier: Modifier){

    InterfazBase()
}@Composable
fun InterfazBase() {

    var cantidad by remember { mutableStateOf("") }
    var cantidadD=cantidad.toDoubleOrNull()?:0.0
    var mensaje by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf(0.0) }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = cantidad,
            onValueChange = {
                    entrada -> cantidad = entrada
                },
            label = {Text("Cantidad")},
            placeholder = {Text("0.0")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )

        )
        Button(
            onClick = {
                resultado= calcularIva(cantidadD)
                mensaje= "El iva de la cantidad introducida es $resultado"
            }
        ) {
            Text("Calcular")
        }
        Text(mensaje)

    }
}

