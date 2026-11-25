package com.example.mensaje

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mensaje.ui.theme.VerdeClaro
import com.example.mensaje.ui.theme.VerdePrincipal

class PruebaActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mensajeRecibido = intent.getStringExtra("mensaje") ?: ""

        setContent {
            MostrarMensaje(mensajeRecibido)
        }
    }

}

@Composable
fun MostrarMensaje(
    mensajeRecibido: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VerdeClaro),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = mensajeRecibido,
            modifier = Modifier.background(VerdePrincipal),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}