package com.example.practicexamenjetpacksaludo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.practicexamenjetpacksaludo.ui.theme.PracticExamenJetpackSALUDOTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticExamenJetpackSALUDOTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeSaludo(
                        modifier= Modifier.fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun HomeSaludo (modifier : Modifier){
    var nombre by remember { mutableStateOf("") }
var saludo by remember { mutableStateOf(" Hola ") }

    Column(
        modifier= Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(saludo)
        Row(
            modifier= Modifier.fillMaxWidth()
        ) {
            TextField(
                value = nombre ,
                onValueChange = {
                    entrada -> nombre = entrada
                },
                label = {Text("Nombre")},
                placeholder = {Text("Nombre")},
            )
            Button(
                onClick = {
                    saludo = "Hola $nombre"
                }
            ) {
                Text("Actualizar")
            }
        }
    }
}

