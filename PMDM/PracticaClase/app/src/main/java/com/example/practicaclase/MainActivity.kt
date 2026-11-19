package com.example.practicaclase

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.practicaclase.ui.theme.PracticaClaseTheme
import kotlin.jvm.java


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaClaseTheme {
                HomeFormulario()
            }
        }
    }
    fun abrirSegundoActivity(usuario: String,admin:String, ciudad: String){
        val intent = Intent(
            this,
            MainActivity2::class.java
        )
        intent
            .putExtra(USUARIO, usuario)
            .putExtra(ADMIN,admin)

        startActivity(intent)
    }
}

@Composable
fun HomeFormulario(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    // estaddos
    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var admin by remember { mutableStateOf(false) }

    val listaOpciones = stringArrayResource(R.array.lista_ciudades).toList()
    var ciudadSeleccionada by remember { mutableStateOf(if (listaOpciones.isNotEmpty()) listaOpciones[0] else "") }

    // errror
    var mensajeError by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("REGISTRO")

        TextField(
            value = usuario,
            onValueChange = { nuevoU -> usuario = nuevoU },
            label = { Text(stringResource(R.string.usuario1)) },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = password,
            onValueChange = { contra -> password = contra.take(MAX_PASS) },
            label = { Text(stringResource(R.string.contraseña)) },
            visualTransformation = PasswordVisualTransformation('*'),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        //  Desplegable: Ciudades
        DesplegableCiudades(
            opciones = listaOpciones,
            opcionSeleccionada = ciudadSeleccionada,
            alSeleccionar = { lc->ciudadSeleccionada=lc },
            etiqueta = "Elige una ciudad",
            modifier = Modifier.fillMaxWidth()
        )

        // Switch de Rol
        SwitchRol(
            adminR = admin,
            onAdminChange = { n -> admin = n }
        )


        Button(
            onClick = {
                val usuarioValido = usuario.trim().length>=MIN_USUARIO
                val ciudadValida = ciudadSeleccionada.isNotEmpty()
                val passwordValida = password.length == 4 && password.isDigitsOnly()

                when {
                    !usuarioValido -> {
                        mensajeError = "El usuario debe tener al menos $MIN_USUARIO caracteres y no tener solo espacios."
                    }
                    !passwordValida -> {
                        mensajeError = "La contraseña debe ser un PINde $MAX_PASS dígitos."
                    }
                    !ciudadValida -> {
                        mensajeError = "Debes seleccionar una ciudad válida."
                    }
                    else -> {
                        //Limpiamos el errorr
                        mensajeError = ""
                        (context as? MainActivity)?.abrirSegundoActivity(usuario.trim(),admin.toString(),ciudadSeleccionada)
                    }
                }
            },
            shape = RoundedCornerShape(100.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.validar))
        }

        // Muestra de error si la valida falla
        if (mensajeError.isNotEmpty()) {
            Text(
                text = mensajeError,
                color = androidx.compose.ui.graphics.Color.Red
            )
        }
    }
}

@Composable
fun SwitchRol(
    adminR: Boolean,
    onAdminChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(stringResource(R.string.admin))
        Switch(
            checked = adminR,
            onCheckedChange = onAdminChange
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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