package vb.progra.ejerciciosdebotones

import android.R.attr.fontWeight
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vb.progra.ejerciciosdebotones.ui.theme.EjerciciosDeBotonesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjerciciosDeBotonesTheme {
            Scaffold(Modifier.fillMaxSize()) {innerPadding ->
                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    EditarPerfil(Modifier.weight(0.5f))
                    Spacer(modifier = Modifier.height(10.dp))
                    Contador(Modifier.weight(0.5f))
                }
                }
            }
        }
    }
}
/*
@Composable
fun Perfilscreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
        Text(text = stringResource(R.string.mi_perfil),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))



    }

}
*/
 
@Composable
fun EditarPerfil(modifier : Modifier = Modifier){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = stringResource(R.string.editar_perfil),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                ,
            colors = ButtonDefaults.buttonColors(Color.Blue,contentColor = Color.White),
            shape=RoundedCornerShape(12.dp)
        ) {
            Text(text = stringResource(R.string.guardar_cambios)
                )

        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(5.dp)
                    .weight(0.5f),

                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Blue),
                border= BorderStroke(1.dp,Color.Blue),
                shape = RoundedCornerShape(12.dp)
            ){
                Text(
                    text = stringResource(R.string.cancelar)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(5.dp)
                    .weight(0.5f),
                colors = ButtonDefaults.buttonColors(containerColor= Color.White, contentColor = Color.Red),
                border= BorderStroke(1.dp,Color.Red),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.borrar)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(Color.Blue,contentColor = Color.White),
            shape=RoundedCornerShape(12.dp),
            enabled = false
        ) {
            Text(text = stringResource(R.string.no_disponible))
        }
    }
}
@Composable
fun Contador(modifier : Modifier = Modifier) {
    var contador by remember {mutableStateOf(1)}
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,


    ) {
        Text(
            text = "CONTADOR",
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Pulsaciones: 0",
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { contador++ },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Blue,contentColor = Color.White),
        ) {
            Text(text = "SUMAR +1")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.White,contentColor = Color.Blue),
            border= BorderStroke(1.dp,Color.Blue)

        ) {
            Text(text = "REINICIAR")
        }

    }
}
@Preview(showBackground = true)
@Composable
fun EditarPerfilPreview() {
    EjerciciosDeBotonesTheme {
        EditarPerfil()
    }
}
@Preview(showBackground = true)
@Composable
fun ContadorPreview() {
    Contador()
}