package com.example.practicaexamenjetpack_ventanas_activity

import android.content.Intent
import android.service.autofill.Transformation
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun PantallaFormulario(modifier: Modifier) {

    var usuarioIntroducido by remember { mutableStateOf("") }
    var contraIntroducida by remember { mutableStateOf("") }
    var esAdmin by remember { mutableStateOf(false) }

    var textoError by remember { mutableStateOf("") }

    // Lista desplegable
    //la listeamos porque es lo que espera el menu una lista
    val listaOpciones = stringArrayResource(R.array.listaCiudades).toList()

    //Dejamos la primera marcada por defecto
    var seleccionada by remember { mutableStateOf(listaOpciones[0]) }
    var Uv by remember { mutableStateOf(false) }
    var usuarioError = "El Usuario debe tener 6 caracteres , no puede estar vaacio ni solo espacio"
    var Cv by remember { mutableStateOf(false) }
    val contraError = "La contraseña debe tener exactamente 4 digitos"

    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 15.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        CampoTexto(

            value = usuarioIntroducido,
            onValueChange = { entrada ->
                usuarioIntroducido = entrada
            },
            label = R.string.Usuario,

            )
        CampoNumero(

            value = contraIntroducida,
            onValueChange = { entrada ->
                contraIntroducida = entrada
            },
            label = R.string.contrase_a

        )
        DesplegableCiudades(
            opciones = listaOpciones,
            opcionSeleccionada = seleccionada,
            alSeleccionar = { nuevaSeleccion ->
                seleccionada = nuevaSeleccion
            },
            etiqueta = stringResource(R.string.sselecciona_una_ciudad)
        )

        SwitchAdmin(
            enunciado = stringResource(R.string.es_admin),

            esAdmin = esAdmin,
            onAdminChange = { nuevoEstado ->
                esAdmin = nuevoEstado
            }
        )
        Button(
            onClick = {
                if (usuarioIntroducido.length < 6 && usuarioIntroducido.isNotBlank()) {
                    textoError = usuarioError
                } else {
                    Uv = true
                }
                if (contraIntroducida.length != 4) {
                    textoError = contraError
                } else {
                    Cv = true
                }
                if (Uv && Cv) {
                    val intent = Intent(context, MainActivity2::class.java).apply {
                        putExtra(EXTRA_USER, usuarioIntroducido)
                        putExtra(EXTRA_ESADMIN, esAdmin)
                    }
                    context.startActivity(intent)
                }
            }
        ) {
            Text(stringResource(R.string.validar))
        }
        Text(
            textoError,
            color = Color.Red
        )
    }

}

@Composable
fun CampoTexto(
    @StringRes label: Int,
    value: String,
    onValueChange: (String) -> Unit,

    ) {
    TextField(
        label = { Text(stringResource(label)) },
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )

    )
}

@Composable
//STRING RES IMPORTANTE
fun CampoNumero(
    @StringRes label: Int,
    value: String,
    onValueChange: (String) -> Unit,

    ) {
    TextField(
        //TEXT DELANTE PARA QUE NO TE DE FALLO
        label = { Text(stringResource(label)) },
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Next,

            ),
        visualTransformation = PasswordVisualTransformation('*')


    )
}

@Composable
fun SwitchAdmin(
    enunciado: String,
    esAdmin: Boolean,
    onAdminChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

    ) {
        Text(enunciado)
        Switch(
            checked = esAdmin,
            onCheckedChange = onAdminChange,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun DesplegableCiudades(
    opciones: List<String>,
    opcionSeleccionada: String,
    alSeleccionar: (String) -> Unit,
    etiqueta: String = "Seleccionar",
    modifier: Modifier = Modifier
) {
    var expandido by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expandido,
        onExpandedChange = { expandido = !expandido },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = opcionSeleccionada,
            onValueChange = {},
            readOnly = true,
            label = { Text(etiqueta) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandido)
            },
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false }
        ) {
            opciones.forEach { opcion ->
                DropdownMenuItem(
                    text = { Text(opcion) },
                    onClick = {
                        alSeleccionar(opcion)
                        expandido = false
                    }
                )
            }
        }
    }
}

@Composable
fun PantallaSecundaria(
    modifier: Modifier,
    onVolver : ()  -> Unit
) {
    Column(
        modifier=Modifier.fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            onClick = onVolver,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Yellow
                , contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ){
            Text("Volver")
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Titulo(title: String) {

    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )

    )

}