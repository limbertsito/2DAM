package com.example.practicaexamenjetpackestados_activities


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicaexamenjetpackestados_activities.ui.theme.PracticaExamenJetpackESTADOS_ACTIVITIESTheme
private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaExamenJetpackESTADOS_ACTIVITIESTheme {
                NumeroIncrementado()
            }
        }
    }
    // Log.v() registra mensajes detallados.
    override fun onResume() {
        Log.v(TAG, "LLAMANDO a on RESUME!")
        super.onResume()
    }
    // Log.i() para mensajes informativos.
    override fun onStart() {
        Log.i(TAG,"COMENZANDO la app de ejemplo")
        super.onStart()
    }

    // Log.e() para mensajes de error.
    override fun onPause() {
        Log.e(TAG,"PAUSANDO la app de ejemplo")
        super.onPause()
    }
    //Log.d() escribe un mensaje de depuración.
    override fun onRestart() {
        Log.d(TAG,"REINCIIANDO la app de ejemplo")
        super.onRestart()
    }

    // Log.w() para advertencias
    override fun onDestroy() {
        Log.w(TAG,"DESTRUYENDOOOOOOOO")
        super.onDestroy()
    }
}
@Composable
fun NumeroIncrementado(){
    var num by rememberSaveable() { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("$num")
        TextButton(
            onClick = {num++},

            ) { Text("Suma 1+") }
    }
}

