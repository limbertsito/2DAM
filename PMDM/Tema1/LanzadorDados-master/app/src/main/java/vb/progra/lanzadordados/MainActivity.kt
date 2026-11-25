package vb.progra.lanzadordados

import android.R.attr.contentDescription
import android.R.attr.onClick
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vb.progra.lanzadordados.ui.theme.LanzadorDadosTheme
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LanzadorDadosTheme {
                LanzarDadosApp()
                }
            }
        }
    }

@Preview
@Composable
fun LanzarDadosApp(){
    //Vamso a crear la vista de la app
    DadosConBotonImagen(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DadosConBotonImagen(modifier: Modifier=Modifier){

    //Definimos la variable de la imagen
    //con esto hacemos que la variable sea mutable
    var resultado by remember { mutableStateOf(1) }
        //Con el by , delegamos  la forma de manejar la propiedad


    var imagen= when(resultado){
        1->R.drawable.dado_1
        2->R.drawable.dado_2
        3->R.drawable.dado_3
        4->R.drawable.dado_4
        5->R.drawable.dado_5
        6->R.drawable.dado_6
        else->R.drawable.dado_1
    }
    Column(
        modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally


    ) {
        Image(
            painter = painterResource(imagen),
            contentDescription="Imagen del dado $resultado"
            )
        Spacer(
            //Espacio entre la imagen y el botón
            modifier = Modifier.height(16.dp)
        )
        Button(
            onClick= {
                //Generamos un número aleatorio entre 1 y 6
                resultado=(1..6).random()
            }
                )
         {
            Text(
                //añadimos el string a los rrecursos mediante el IDE
                stringResource(R.string.textoDelBoton)
            )
        }
    }
}
