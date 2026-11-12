package com.example.practicaclase

import android.R.attr.checked
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicaclase.ui.theme.PracticaClaseTheme

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
}

@Composable
fun HomeFormulario(modifier: Modifier = Modifier) {

    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }
    var admin by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            "REGISTRO"
        )
        TextField(
            value = usuario,
            onValueChange = {
                    newUser -> usuario = newUser
            },
            label = {Text(stringResource(R.string.usuario1))},
            modifier = Modifier.padding(16.dp)
        )
        TextField(
            value = password,
            onValueChange = {
                    newPass -> password = newPass
            },
            label = {Text(stringResource(R.string.contraseña))},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.padding(16.dp)

        )
        TextField(
            value = ciudad,
            onValueChange = {
                newCiudad -> ciudad = newCiudad
            },
            label= {
                Text(stringResource(R.string.ciudades))
            }
        )
        SwitchRol(
            adminR = admin,
            onAdminChange = {
                cambio -> admin = cambio
            },modifier= Modifier

        )
        Button(
            onClick = {},
            shape = RoundedCornerShape(100.dp)

        ){
            Text(stringResource(R.string.validar))
        }



    }
}
@Composable
fun SwitchRol(
    adminR : Boolean,
    onAdminChange :(Boolean)->Unit,
    modifier: Modifier = Modifier
){
    Row(
       modifier = modifier.size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(stringResource(R.string.admin))
        Spacer(modifier = modifier.width(10.dp))
        Switch(
            checked=adminR,
            onCheckedChange = onAdminChange,
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.CenterStart)
        )
    }
}