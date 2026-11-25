package com.example.practicaexamenjetpack_ventanas_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier


import com.example.practicaexamenjetpack_ventanas_activity.ui.theme.PracticaExamenJetpack_VENTANAS_ACTIVITYTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaExamenJetpack_VENTANAS_ACTIVITYTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaFormulario(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                    )

                }
            }
        }
    }
}

