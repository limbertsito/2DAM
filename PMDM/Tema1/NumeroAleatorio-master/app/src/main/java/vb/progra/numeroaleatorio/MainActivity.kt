package vb.progra.numeroaleatorio

import android.R.attr.enabled
import android.R.attr.onClick
import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vb.progra.numeroaleatorio.ui.theme.NumeroAleatorioTheme
import java.lang.Compiler.enable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NumeroAleatorioTheme {
                BotonesAleatorios()
            }
        }
    }
}

@Composable
fun BotonesAleatorios() {
    val numRndm = (10..20).random()
    var numR by remember { mutableStateOf(numRndm) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val color = when (numR) {
            in 10..20 -> Color.Green
            in 4..9 -> Color.Yellow
            in 1..3 -> Color.Red
            else -> Color.Black
        }
        Text(
            text = "$numR",
            color = color,
            fontSize = 120.sp
        )


        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Button(
                onClick = {

                        numR--


                },
                enabled = numR > 0
            ) {
                Text(text = stringResource(R.string.pulsar))
            }
            Spacer(modifier = Modifier.width(15.dp))
            Button(
                onClick = {
                    numR = numRndm
                }
            ) {
                Text(text = stringResource(R.string.reiniciar))

            }
        }


}
}

