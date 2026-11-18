package com.example.practicaexamenjetpack

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicaexamenjetpack.ui.theme.PracticaExamenJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaExamenJetpackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Bienvenida(
                        msg = "Bienvenido ",
                        curso="2DAM",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Bienvenida(msg:String,curso: String ,modifier: Modifier = Modifier){

    Box(modifier = modifier.fillMaxSize()){
        Image(
            painter = painterResource(R.drawable.logo_venancio),
            contentDescription = stringResource(R.string.logo_de_venancio)
        )
        Image(

            painter = painterResource(R.drawable.fondo),
            contentDescription = stringResource(R.string.fondo_de_imagen_android),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.5f,


            )
        Column(modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Text(msg, modifier = modifier,
                fontSize = 70.sp)
            Text(curso,modifier=modifier,
                fontSize = 100.sp,
                color = Color.Blue)


        }
    }
}

