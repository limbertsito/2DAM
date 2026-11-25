package vb.progra.ejerciciosaludo

import android.R.string.no
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import vb.progra.ejerciciosaludo.ui.theme.EjercicioSaludoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjercicioSaludoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier){
    var nombreIntroducido by remember { mutableStateOf("Nombre") }

    var saludo by remember { mutableStateOf("Hola ") }
    Column(modifier = modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = saludo)
        Row(){
            TextField(
                value= nombreIntroducido,
                onValueChange = {
                        nuevoTexto ->
                    nombreIntroducido= nuevoTexto
                },
                label = {
                    Text("Nombre")
                }
            )


            Button(onClick = {
                saludo  = "Hola "+ nombreIntroducido
            },
                shape = RoundedCornerShape(100.dp),){
                Text(
                    text = "Actualizar"
                )

            }
        }
    }




}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen(){
    HomeScreen()
}