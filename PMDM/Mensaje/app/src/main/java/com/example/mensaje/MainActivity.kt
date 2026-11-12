package com.example.mensaje

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mensaje.ui.theme.Blanco
import com.example.mensaje.ui.theme.EXTRA_MENSAJE
import com.example.mensaje.ui.theme.MAX_CARACTERES
import com.example.mensaje.ui.theme.MensajeTheme
import com.example.mensaje.ui.theme.NaranjaClaro
import com.example.mensaje.ui.theme.NaranjaPrincipal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MensajeTheme {
                home1(this@MainActivity)
            }
        }
    }
}

@Composable
fun home1(activity: MainActivity) {
    var mensaje by rememberSaveable { mutableStateOf("") }

        Column(
            modifier = Modifier
                .background(NaranjaClaro)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        )
        {
            Text(text = stringResource(R.string.enviar_mensaje))
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = mensaje,
                onValueChange = { newText ->
                    //constante limite de caracteres
                    if(mensaje.length <= MAX_CARACTERES) {
                        mensaje = newText
                    }
                },
                label = { Text(stringResource(R.string.mensaje)) },
                modifier = Modifier.padding(16.dp)
            )
            Button(
                onClick = {
                //creamos el intent
                    val intent = Intent(activity, PruebaActivity::class.java)
                    //COnstante mensaje
                    intent.putExtra(EXTRA_MENSAJE, mensaje)
                    activity.startActivity(intent)
                },
                enabled = mensaje.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NaranjaPrincipal,
                    contentColor = Blanco
                ),
                modifier = Modifier,

                content = {
                    Text(text = "Enviar")
                }

            )
        }


}


