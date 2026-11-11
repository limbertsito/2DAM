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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.mensaje.ui.theme.MensajeTheme

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
    var mensaje by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .background(Color(0xFFFFA500))
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
                    mensaje = newText
                },
                label = { Text(stringResource(R.string.mensaje)) },
                modifier = Modifier.padding(16.dp)
            )
            Button(
                onClick = {
                //creamos el intent
                    val intent = Intent(activity, PruebaActivity::class.java)
                    intent.putExtra("mensaje", mensaje)
                    activity.startActivity(intent)
                },
                enabled = mensaje.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9C7352),
                    contentColor = Color.White
                ),
                modifier = Modifier,

                content = {
                    Text(text = "Enviar")
                }

            )
        }


}
