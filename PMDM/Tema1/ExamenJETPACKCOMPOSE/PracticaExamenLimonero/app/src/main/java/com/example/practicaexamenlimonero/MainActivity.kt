package com.example.practicaexamenlimonero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicaexamenlimonero.ui.theme.PracticaExamenLimoneroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaExamenLimoneroTheme {
                Scaffold(
                    topBar={
                        TopBarLimon(modifier=Modifier.fillMaxSize())
                    }
                ) {innerPadding ->
                    Surface(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                    ) {
                        HomeLimon(modifier = Modifier.fillMaxSize())
                    }
                }
            }
        }
    }
}

@Composable
fun HomeLimon(modifier: Modifier){
    var aleatorio by remember { mutableStateOf(1) }
    var texto by remember { mutableStateOf("") }
    var contador by remember { mutableStateOf(1) }

    var imagen by remember { mutableStateOf((R.drawable.lemon_tree)) }
    when (contador){
        1 -> {
            imagen = (R.drawable.lemon_tree)
            texto = stringResource(R.string.pulsa_para_obtener_limones)
        }
       2 -> {
            imagen = (R.drawable.lemon_squeeze)
            texto = stringResource(R.string.pulsa_x_veces_para_exprimir)

        }
        3-> {
            imagen = (R.drawable.lemon_drink)
            texto = stringResource(R.string.pulsa_para_beber_la_limonada)
        }
        4-> {
            imagen = (R.drawable.lemon_restart)
            texto = stringResource(R.string.pulsa_para_volver_a_inciiar)
        }
        else ->{
            imagen=(R.drawable.lemon_tree)
            texto = stringResource(R.string.pulsa_para_obtener_limones)

        }
    }


    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    when(contador){
                        1->{
                            contador++
                            aleatorio= (2..4).random()

                        }
                        2->{
                            aleatorio--
                            if(aleatorio==0){
                                contador ++
                            }

                        }
                        3->{contador ++}
                        4->{contador =1}
                    }

                }
            ) {
                Image(
                    painter = painterResource(imagen),
                    contentDescription = "Limonero arbol"
                )
            }
            Spacer(modifier=Modifier.height(15.dp))
            Text(texto)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarLimon(modifier :Modifier){

    CenterAlignedTopAppBar(
        title = {
            Text(
                "Limonada",
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            MaterialTheme.colorScheme.primary,

        )
    )
}