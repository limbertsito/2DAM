package com.example.laboratoriodecarteles.ui.componentes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import com.example.laboratoriodecarteles.R
import java.time.*
import java.time.format.*
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
internal fun LocalDate.textoLargo(): String =
    format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.forLanguageTag("es-ES")))

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorFecha(fecha: LocalDate?, alElegir: (LocalDate) -> Unit) {
    var abierto by rememberSaveable { mutableStateOf(false) }
    OutlinedButton(onClick = { abierto = true }) {
        Text(fecha?.textoLargo() ?: stringResource(R.string.elegir_fecha))
    }
    if (abierto) {
        val selector = rememberDatePickerState(
            initialSelectedDateMillis = fecha?.atStartOfDay(ZoneOffset.UTC)?.toInstant()?.toEpochMilli()
        )
        DatePickerDialog(
            onDismissRequest = { abierto = false },
            confirmButton = {
                TextButton(enabled = selector.selectedDateMillis != null, onClick = {
                    selector.selectedDateMillis?.let { milisegundos ->
                        alElegir(Instant.ofEpochMilli(milisegundos).atZone(ZoneOffset.UTC).toLocalDate())
                    }
                    abierto = false
                }) { Text(stringResource(android.R.string.ok)) }
            },
            dismissButton = {
                TextButton(onClick = { abierto = false }) { Text(stringResource(android.R.string.cancel)) }
            }
        ) { DatePicker(state = selector) }
    }
}
