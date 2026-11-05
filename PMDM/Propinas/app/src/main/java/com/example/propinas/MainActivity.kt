package com.example.propinas

import android.os.Bundle
import android.widget.Switch
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberOverscrollEffect
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import com.example.propinas.ui.theme.PropinasTheme
import kotlin.math.round

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
    var importeInput by remember { mutableStateOf("") }
    val amount = importeInput.toDoubleOrNull() ?: 0.0
    var porcentajePropina by remember { mutableStateOf("") }
    var redondeo by remember { mutableStateOf(false) }
    val tip = calcularPropina(amount, porcentajePropina.toDoubleOrNull() ?: 0.0,redondeo)


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
        EditNumberField(
            value = importeInput,
            onValueChange = { cantidad -> importeInput = cantidad },
            label = R.string.bill_amount,
            modifier = Modifier,
            imeAction = ImeAction.Next


        )
        Spacer(modifier = Modifier.height(24.dp))
        EditNumberField(
            value = porcentajePropina,
            onValueChange = { cantidad -> porcentajePropina = cantidad },
            label = R.string.how_was_the_service,
            modifier = Modifier,
            imeAction = ImeAction.Done
        )
        Text(
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(150.dp))
        RedondeoSwitch(
            roundUp = redondeo,
            onRoundUpChanged = { roundUp -> redondeo = roundUp   },
            modifier = Modifier,

            )
    }
}

/**
 * Función que calcula la propina a partir de un porcentaje
 */
private fun calcularPropina(cantidad: Double, porcentajePropina: Double = 15.0,redondeo: Boolean ): String {
    var propina = porcentajePropina / 100 * cantidad

    if (redondeo) {
        propina = kotlin.math.ceil(propina)
    }
    return NumberFormat.getCurrencyInstance().format(propina)
}
@Composable
fun RedondeoSwitch(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier

){
    Row(
        modifier = modifier
            .fillMaxWidth()

            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.round_up_tip))
        Switch(
            checked = roundUp,
            onCheckedChange = onRoundUpChanged,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }
}
@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    modifier: Modifier,
    imeAction: ImeAction = ImeAction.Default
) {


    TextField(
        value = value,
        onValueChange =
            //con esto hcemos que lo que escriba el usuario se guarde en la variable importeInput y se vea
            onValueChange,
        singleLine = true,
        label = { Text(stringResource(label)) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType
                .Number, imeAction = imeAction
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun PropinasLayoutPreview() {
    PropinasTheme {
        PropinasLayout()
    }
}
