package com.example.practicaexamenjetpackdados

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicaexamenjetpackdados.ui.theme.PracticaExamenJetpackDADOSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaExamenJetpackDADOSTheme {
                    HomeDados()

            }
        }
    }
}

@Composable
fun HomeDados(){
        DadosConBoton(
            modifier= Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )

}

@Composable
fun DadosConBoton(modifier: Modifier) {

    //Declaramos la variables fuera del onclick
    var numero by rememberSaveable() { mutableStateOf(1) }
    val imagen= when(numero){
        1->  R.drawable.dado_1
        2 ->  R.drawable.dado_2
        3 -> R.drawable.dado_3
        4 -> R.drawable.dado_4
        5 -> R.drawable.dado_5
        6 ->R.drawable.dado_6

        //el else tine qeu ser entero porqeu sino nos da fallo en el id de la imagen
        else -> R.drawable.dado_1
    }
    Column(
        modifier = Modifier.fillMaxSize()
        , horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Image(
            //Aqui al mostrar la imagen tendremos que mostrar la imagen
            painter = painterResource(imagen),
            contentDescription = stringResource(R.string.imagen_dado_1),
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                 numero= (1..6).random()


            },
            colors= ButtonDefaults.buttonColors(
                containerColor = Color.Yellow
                ,Color.White
            )
        ) {
            Text(stringResource(R.string.lanzar))

        }
    }
}

