package com.example.practicaexamenjetpack_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicaexamenjetpack_activity.ui.theme.PracticaExamenJetpack_ACTIVITYTheme
import com.example.practicaexamenjetpack_activity.ui.theme.VariosActivityTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Recuperamos el dato que viene del intent , puede ser nulo por lo que operamos con elvis
        //Recogemos siempre aqui los datos qeu recogemos del origen
        val mensajeRecibido = intent.getStringExtra(EXTRA_MENSAJE) ?: "Sin mensaje"
        setContent {

            //Aqui le pasamos el booleano que hara que to-do coja la paleta de color verde
            VariosActivityTheme(usarTemaVerde = true){
                Scaffold(
                    modifier = Modifier.fillMaxSize() ,
                    //Aqui se pondra el color verde de fondo
                    containerColor = MaterialTheme.colorScheme.background
                   , topBar = {
                       PantallaTop(stringResource(R.string.actividad_2))
                    }
                    ) { innerPadding ->

                    SegundaPantalla(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                       mensaje =  mensajeRecibido,
                       onVolver = {
                           //Cierra el activity y volvemos
                           finish()
                       },

                    )

                }
            }
        }
    }
}