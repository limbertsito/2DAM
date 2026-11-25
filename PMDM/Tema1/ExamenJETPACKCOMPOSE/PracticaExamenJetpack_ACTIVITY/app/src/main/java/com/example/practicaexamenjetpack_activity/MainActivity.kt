package com.example.practicaexamenjetpack_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold

import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

import com.example.practicaexamenjetpack_activity.ui.theme.VariosActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VariosActivityTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ,
                    //con esto le aplicamos el fondo naranja claro a nuestra pantalla
                    containerColor = MaterialTheme.colorScheme.background
               , topBar = {
                   PantallaTop(stringResource(R.string.actividad_1))
                    }
                ) { innerPadding ->
                    HomeSaludo(modifier= Modifier
                        .fillMaxSize()
                        .padding(innerPadding))

                }
            }
        }
    }
}

