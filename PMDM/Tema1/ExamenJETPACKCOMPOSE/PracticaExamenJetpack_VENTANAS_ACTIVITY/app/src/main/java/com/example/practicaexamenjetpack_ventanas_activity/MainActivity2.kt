package com.example.practicaexamenjetpack_ventanas_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicaexamenjetpack_ventanas_activity.ui.theme.PracticaExamenJetpack_VENTANAS_ACTIVITYTheme
import com.example.practicaexamenjetpack_ventanas_activity.ui.theme.VariosActivityTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val usuarioEscrito = intent.getStringExtra(EXTRA_USER)?:""
        val esAdminRecibido = intent.getBooleanExtra(EXTRA_ESADMIN,false)
        setContent {
            VariosActivityTheme(esAdminRecibido) {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        Titulo("$usuarioEscrito")
                    }
                    ) { innerPadding ->
                    PantallaSecundaria(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding,
                            ),
                        onVolver = {
                            finish()
                        }
                    )

                }
            }
        }
        }
    }
