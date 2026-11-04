package com.example.propinas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import com.example.propinas.ui.theme.PropinasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PropinasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    PropinasLayout()
                }
            }
        }
    }
}

/**
 * Elemento composable principal de la aplicación
 */
@Composable
fun PropinasLayout() {
    var amountInput by remember { mutableStateOf("") }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tip = calcularPropina(amount)

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        EditNumberField(modifier = Modifier)
        Text(
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(150.dp))
    }
}

/**
 * Función que calcula la propina a partir de un porcentaje
 */
private fun calcularPropina(cantidad: Double, porcentajePropina: Double = 15.0): String {
    val propina = porcentajePropina / 100 * cantidad
    return NumberFormat.getCurrencyInstance().format(propina)
}
@Composable
fun EditNumberField(modifier: Modifier){
    var importeInput by remember { mutableStateOf("") }

    var importe = importeInput.toDoubleOrNull() ?: 0.0
    val tip = calcularPropina(importe)

    TextField(
        value = "",
        onValueChange = {
      //con esto hcemos que lo que escriba el usuario se guarde en la variable importeInput y se vea
            cantidad -> importeInput = cantidad
        },
        singleLine = true,
        label = { Text(stringResource(R.string.bill_amount)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier =modifier
    )
}
@Preview(showBackground = true)
@Composable
fun PropinasLayoutPreview() {
    PropinasTheme {
        PropinasLayout()
    }
}
