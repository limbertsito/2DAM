package com.example.practicaexamenjetpack_propinas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.sourceInformationMarkerEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicaexamenjetpack_propinas.ui.theme.PracticaExamenJetpack_PROPINASTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaExamenJetpack_PROPINASTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PropinasInterfaz(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding))
                }
            }
        }
    }
}
@Composable
fun PropinasInterfaz(

    modifier: Modifier = Modifier
){

    //Atributos inmutables y  llamamos al metood de calcular propina
    var importeIntroducido by remember { mutableStateOf("") }
    val importe= importeIntroducido.toDoubleOrNull() ?: 0.0
    val tip = calcularPropina(importe)

    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(R.string.claculadora_de_propinas))

        EditNumberField(
            label = R.string.cantidad,
            value= importeIntroducido,
            onValueChange = {
                cantidad -> importeIntroducido = cantidad
            },



        )
        Spacer(Modifier.height(10.dp))

        Text(stringResource(R.string.propina ,tip.toString()))
    }

}

@Composable
fun EditNumberField(
    @StringRes label: Int,
    value : String,
    onValueChange : (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange  = onValueChange,
        singleLine = true,
        label = {Text(stringResource(label))},
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number

        )
    )
}

