package com.example.practicaexamejetpackcifrado

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaPrincipalScreen(
    onCodigoCorrecto: (destino: DestinoCanal, mensaje: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var mensaje by remember { mutableStateOf("") }
    var codigo by remember { mutableStateOf("") }

    // Función interna para manejar la pulsación de teclas y la comprobación automática
    fun alPulsarDigito(digito: String) {
        if (codigo.length < LONGITUD_PIN) {
            val nuevoCodigo = codigo + digito
            codigo = nuevoCodigo

            // Al llegar al 4º dígito se valida automáticamente
            if (nuevoCodigo.length == LONGITUD_PIN) {
                val destino = validarCodigo(nuevoCodigo)

                // Si el mensaje NO está vacío/espacios y el código es válido, notificamos al callback
                if (mensaje.isNotBlank() && destino != DestinoCanal.INVALIDO) {
                    onCodigoCorrecto(destino, mensaje.trim())
                }

                // En cualquier caso (erróneo o enviado), limpiamos el código para permitir otro intento
                codigo = ""
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "OPERACIÓN CÓDIGO SECRETO",
            style = MaterialTheme.typography.titleLarge
        )

        // 1. Campo de texto para el mensaje libre
        OutlinedTextField(
            value = mensaje,
            onValueChange = { mensaje = it },
            label = { Text("Escribe tu mensaje confidencial") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 2. Indicador visual (ej. "**--")
        Text(
            text = obtenerIndicadorVisual(codigo),
            style = MaterialTheme.typography.headlineMedium
        )

        // 3. Teclado Numérico de 4 filas
        TecladoNumerico(
            onNumeroPulsado = { digito -> alPulsarDigito(digito) },
            onBorrar = { if (codigo.isNotEmpty()) codigo = codigo.dropLast(1) },
            onLimpiar = { codigo = "" }
        )
    }
}

@Composable
fun TecladoNumerico(
    onNumeroPulsado: (String) -> Unit,
    onBorrar: () -> Unit,
    onLimpiar: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FilaTeclado(listOf("1", "2", "3"), onNumeroPulsado)
        FilaTeclado(listOf("4", "5", "6"), onNumeroPulsado)
        FilaTeclado(listOf("7", "8", "9"), onNumeroPulsado)

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = onLimpiar) { Text("C") }
            Button(onClick = { onNumeroPulsado("0") }) { Text("0") }
            Button(onClick = onBorrar) { Text("←") }
        }
    }
}

@Composable
fun FilaTeclado(numeros: List<String>, onNumeroPulsado: (String) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        for (num in numeros) {
            Button(onClick = { onNumeroPulsado(num) }) {
                Text(num)
            }
        }
    }
}
@Composable
fun MayusculasScreen(
    mensajeMayusculas: String,
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "CANAL ALFA",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = mensajeMayusculas,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onVolver) {
            Text("Volver al Centro de Control")
        }
    }
}

@Composable
fun CifradoScreen(
    mensajeCifrado: String,
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "CANAL BRAVO",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = mensajeCifrado,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onVolver) {
            Text("Volver al Centro de Control")
        }
    }
}