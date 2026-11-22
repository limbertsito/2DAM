package com.example.pruebaexamendi

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pruebaexamendi.ui.theme.PruebaExamenDITheme

/**
 * Composable reutilizable sin estado propio que representa la tarjeta individual de un hábito.
 * Muestra el nombre (tachado si está realizado), categoría, checkbox de estado y un botón de borrar.
 * Además, incluye una animación para expandir su descripción al pulsar sobre la fila.
 *
 * @param habito Objeto [Habito] que contiene los datos del hábito (id, nombre, categoría, etc.).
 * @param onToggle Callback ejecutado al marcar o desmarcar el Checkbox del hábito.
 * @param onDelete Callback ejecutado al pulsar el botón de eliminar.
 * @param modifier Modificador opcional para personalizar el diseño del composable.
 */
@Composable
fun HabitoItem(
    habito: Habito,
    onToggle: (Boolean) -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var expandido by remember { mutableStateOf(false) }

    val backgroundColor by animateColorAsState(
        targetValue = if (habito.realizado) {
            MaterialTheme.colorScheme.secondaryContainer
        } else {
            MaterialTheme.colorScheme.surface
        },
        label = "ColorDeFondoAnimado",
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expandido = !expandido },
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = habito.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (habito.realizado) TextDecoration.LineThrough else null,
                )
                Text(
                    text = stringResource(R.string.category_prefix, stringResource(habito.categoria.etiquetaRes)),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                )
                
                AnimatedVisibility(visible = expandido && habito.descripcion.isNotBlank()) {
                    Text(
                        text = habito.descripcion, 
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp),
                    )
                }
            }
            Checkbox(
                checked = habito.realizado,
                onCheckedChange = onToggle,
                modifier = Modifier.sizeIn(minWidth = 48.dp, minHeight = 48.dp),
            )
            
            val deleteDesc = stringResource(R.string.delete_habit_desc, habito.nombre)
            IconButton(
                onClick = onDelete,
                modifier = Modifier
                    .sizeIn(minWidth = 48.dp, minHeight = 48.dp)
                    .semantics { contentDescription = deleteDesc },
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HabitoItemPreview() {
    PruebaExamenDITheme {
        HabitoItem(
            habito = Habito(
                id = 1,
                nombre = "Aprender Jetpack Compose",
                descripcion = "Practicar creando interfaces paso a paso",
                realizado = true,
                categoria = Categoria.ESTUDIO,
            ),
            onToggle = {},
            onDelete = {},
        )
    }
}
