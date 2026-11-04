package vb.progra.ejercicioentregableprogramacin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vb.progra.ejercicioentregableprogramacin.ui.theme.EjercicioEntregableProgramaciónTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjercicioEntregableProgramaciónTheme {

                   CalculadoraIva()

            }
        }
    }
}

@Composable
fun CalculadoraIva() {
    var cantidadaIntroducida by remember() { mutableStateOf("") }
    var ivaIntroducido by remember() { mutableStateOf("") }
    var baseImporte by remember() { mutableStateOf("Base imponible: ") }
    var iva by remember() { mutableStateOf("IVA: ") }
    var total by remember() { mutableStateOf("Total: ") }

    Column(
        modifier = Modifier.fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculadora de IVA",
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = cantidadaIntroducida,
            onValueChange = { cantidad ->
                cantidadaIntroducida = cantidad

            },
            label = {
                Text(
                    "Cantidad final:"
                )
            }
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = ivaIntroducido,
            onValueChange = { iva ->
                ivaIntroducido = iva
            },
            label = {
                Text("IVA &:")
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {

        }
        ) {
            Text("Calcular")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = baseImporte)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = iva)

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EjercicioEntregableProgramaciónTheme {
        CalculadoraIva()
    }
}