package com.example.practicaexamejetpackcifrado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicaexamejetpackcifrado.ui.theme.VariosActivityTheme

class CifradoScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // 1. Obtenemos el mensaje original del Intent
        val mensajeOriginal = intent.getStringExtra(EXTRA_MENSAJE) ?: ""

        // 2. Transformación Unicode +1 usando la función de LogicaCodigo.kt
        val mensajeCifrado = cifrarTextoUnicode(mensajeOriginal)

        setContent {
            VariosActivityTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CifradoScreen(
                        mensajeCifrado = mensajeCifrado,
                        onVolver = { finish() }
                    )
                }
            }
        }
    }
}