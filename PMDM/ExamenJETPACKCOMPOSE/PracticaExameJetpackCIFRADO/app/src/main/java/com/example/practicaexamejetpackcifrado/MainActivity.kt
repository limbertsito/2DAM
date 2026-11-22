package com.example.practicaexamejetpackcifrado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.practicaexamejetpackcifrado.ui.theme.PracticaExameJetpackCIFRADOTheme
import kotlin.jvm.java

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OperacionCodigoTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PantallaPrincipalScreen(
                        onCodigoCorrecto = { destino, mensaje ->
                            navegarACanal(destino, mensaje)
                        }
                    )
                }
            }
        }
    }

    private fun navegarACanal(destino: DestinoCanal, mensaje: String) {
        val actividadDestino = when (destino) {
            DestinoCanal.ALFA -> MayusculasActivity::class.java
            DestinoCanal.BRAVO -> CifradoActivity::class.java
            DestinoCanal.INVALIDO -> return
        }

        val intent = Intent(this, actividadDestino).apply {
            putExtra(EXTRA_MENSAJE, mensaje)
        }
        startActivity(intent)
    }
}

