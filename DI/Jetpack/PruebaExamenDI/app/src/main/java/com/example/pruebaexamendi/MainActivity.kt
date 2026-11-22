package com.example.pruebaexamendi

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pruebaexamendi.ui.theme.PruebaExamenDITheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaExamenDITheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text(stringResource(R.string.habitos_title), style = MaterialTheme.typography.titleLarge) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                ) { innerPadding ->
                    HabitosScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitosScreen(
    modifier: Modifier = Modifier,
) {
    // Cargar textos iniciales desde strings.xml para cumplir con la regla de no hardcodear textos
    val habitoAguaNombre = stringResource(R.string.habito_agua_nombre)
    val habitoComposeNombre = stringResource(R.string.habito_compose_nombre)
    val habitoComposeDesc = stringResource(R.string.habito_compose_desc)
    val habitoCamaNombre = stringResource(R.string.habito_cama_nombre)
    val habitoPasearNombre = stringResource(R.string.habito_pasear_nombre)

    // Estados
    var filtroSeleccionado by rememberSaveable { mutableStateOf(R.string.filter_all) }
    var textoNuevoHabito by rememberSaveable { mutableStateOf("") }
    
    // Lista inicial de hábitos por defecto con uno por categoría
    val habitos = remember {
        mutableStateListOf(
            Habito(
                id = 1,
                nombre = habitoAguaNombre,
                descripcion = "",
                realizado = false,
                categoria = Categoria.SALUD,
            ),
            Habito(
                id = 2,
                nombre = habitoComposeNombre,
                descripcion = habitoComposeDesc,
                realizado = false,
                categoria = Categoria.ESTUDIO,
            ),
            Habito(
                id = 3,
                nombre = habitoCamaNombre,
                descripcion = "",
                realizado = false,
                categoria = Categoria.HOGAR,
            ),
            Habito(
                id = 4,
                nombre = habitoPasearNombre,
                descripcion = "",
                realizado = false,
                categoria = Categoria.OCIO,
            ),
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        // Card superior para el progreso animado
        val totalHabitos = habitos.size
        val habitosCompletados = habitos.count { it.realizado }
        ProgresoCard(
            total = totalHabitos,
            completados = habitosCompletados,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 1. LazyRow de filtros
        val categoriasRes = listOf(R.string.filter_all) + Categoria.entries.map { it.etiquetaRes }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(categoriasRes) { categoriaRes ->
                val categoriaTexto = stringResource(categoriaRes)
                FilterChip(
                    selected = filtroSeleccionado == categoriaRes,
                    onClick = { filtroSeleccionado = categoriaRes },
                    modifier = Modifier.sizeIn(minWidth = 48.dp, minHeight = 48.dp),
                    label = { 
                        Text(
                            text = categoriaTexto, 
                            style = if (filtroSeleccionado == categoriaRes) {
                                MaterialTheme.typography.labelLarge.copy(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                            } else {
                                MaterialTheme.typography.labelLarge
                            }
                        ) 
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 2. TextField y botón "Añadir"
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                value = textoNuevoHabito,
                onValueChange = { textoNuevoHabito = it },
                modifier = Modifier.weight(1f),
                label = { Text(stringResource(R.string.new_habit_label)) },
                singleLine = true,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (textoNuevoHabito.isNotBlank()) {
                        val nuevoId = (habitos.maxOfOrNull { it.id } ?: 0) + 1
                        val categoriaAsignada = if (filtroSeleccionado == R.string.filter_all) {
                            Categoria.SALUD
                        } else {
                            Categoria.entries.find { it.etiquetaRes == filtroSeleccionado } ?: Categoria.SALUD
                        }

                        habitos.add(
                            Habito(
                                id = nuevoId,
                                nombre = textoNuevoHabito.trim(),
                                descripcion = "",
                                realizado = false,
                                categoria = categoriaAsignada
                            )
                        )
                        textoNuevoHabito = ""
                    }
                },
                modifier = Modifier.sizeIn(minWidth = 48.dp, minHeight = 48.dp),
            ) {
                Text(stringResource(R.string.add_button))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Filtrado de la lista
        val habitosFiltrados = if (filtroSeleccionado == R.string.filter_all) {
            habitos
        } else {
            habitos.filter { it.categoria.etiquetaRes == filtroSeleccionado }
        }

        // 3. LazyColumn
        ListaHabitos(
            habitos = habitosFiltrados,
            onHabitoCheckedChange = { idHabito, isChecked ->
                val index = habitos.indexOfFirst { it.id == idHabito }
                if (index != -1) {
                    habitos[index] = habitos[index].copy(realizado = isChecked)
                }
            },
            onEliminarHabito = { idHabito ->
                habitos.removeAll { it.id == idHabito }
            },
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
fun ProgresoCard(
    total: Int,
    completados: Int,
    modifier: Modifier = Modifier,
) {
    val progreso = if (total > 0) completados.toFloat() / total.toFloat() else 0f
    
    val progresoAnimado by animateFloatAsState(
        targetValue = progreso,
        label = "ProgresoAnimado",
    )

    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.progress_text, completados, total),
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { progresoAnimado },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
fun ListaHabitos(
    habitos: List<Habito>,
    onHabitoCheckedChange: (Int, Boolean) -> Unit,
    onEliminarHabito: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            items = habitos,
            key = { it.id }
        ) { habito ->
            HabitoItem(
                habito = habito,
                onToggle = { isChecked ->
                    onHabitoCheckedChange(habito.id, isChecked)
                },
                onDelete = {
                    onEliminarHabito(habito.id)
                }
            )
        }
    }
}

@Preview(showBackground = true, name = "Modo Oscuro", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, name = "Modo Claro", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HabitosScreenPreview() {
    PruebaExamenDITheme {
        Surface {
            HabitosScreen()
        }
    }
}
