package com.example.practicaexamenjetpackpropinas

import android.os.Bundle

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp

import com.example.practicaexamenjetpackpropinas.ui.theme.PracticaExamenJetpackPROPINASTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaExamenJetpackPROPINASTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        PropinasInterfaz(modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                        )
                }
            }
        }
    }
}

@Composable
fun PropinasInterfaz(modifier: Modifier){

    var importeEntrada by remember { mutableStateOf("") }
    var propinasEntrada by remember { mutableStateOf("") }

    var estadoSwitch by remember { mutableStateOf(false) }

    val importe = importeEntrada.toDoubleOrNull() ?: 0.0
    val propinasPorcentaje = propinasEntrada.toDoubleOrNull() ?:0.0

    val propina = calcularPropina(importe, propinasPorcentaje,estadoSwitch)
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(R.string.calculadora_de_propina),
            modifier= Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        EditTextField1(
            label = R.string.cantidad_dinero,
            value= importeEntrada,
            onValueChange = {
                entrada -> importeEntrada = entrada
            },
            modifier
        )
        Spacer(modifier= Modifier.height(10.dp))

        EditTextField2(
            label = R.string.de_propina,
            value= propinasEntrada,
            onValueChange = {
                entrada -> propinasEntrada = entrada
            },
            modifier
        )
        Spacer(Modifier.height(10.dp))
        Text(stringResource(R.string.propina1, propina))
        Spacer(Modifier.height(10.dp))

        SwitchRedondeo(
            redondeo = estadoSwitch,
            redondeoChanged = {
                nuevoEstado -> estadoSwitch = nuevoEstado
            }
        )
    }
}

@Composable
fun EditTextField1(
    @StringRes label : Int,
    value :String,
    onValueChange : (String)->Unit,

    modifier: Modifier
){
    TextField(
        label ={ Text(stringResource(label))},
        value = value,
        onValueChange = onValueChange,

        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next

        ),

        modifier = Modifier.fillMaxWidth()
    )

}
@Composable
fun EditTextField2(
    @StringRes label : Int,
    value :String,
    onValueChange : (String)->Unit,

    modifier: Modifier
){
    TextField(
        label ={ Text(stringResource(label))},
        value = value,
        onValueChange = onValueChange,

        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Send

        ),

        modifier = Modifier.fillMaxWidth()
    )

}
@Composable
fun SwitchRedondeo(
    redondeo : Boolean,
    redondeoChanged : (Boolean) -> Unit,
    modifier: Modifier= Modifier
){
    Row(
        modifier= modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

    ) {
        Text(stringResource(R.string.redondeo))
        Switch(
            checked = redondeo,
            onCheckedChange = redondeoChanged,
            modifier= modifier.fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }
}