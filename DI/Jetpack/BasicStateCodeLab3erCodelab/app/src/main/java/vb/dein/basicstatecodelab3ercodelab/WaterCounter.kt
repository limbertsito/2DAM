package vb.dein.basicstatecodelab3ercodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
  /*
    Column(modifier = modifier.padding(16.dp)) {
        var count by remember { mutableStateOf(0) }

        if (count > 0) {
            var showTask by remember { mutableStateOf(true) }
            if (showTask) {
                WellnessTaskItem(
                    onClose = { showTask = false },//Con esto hacemos que cuando se pulse se quite la tarea
                    taskName = "Has dado tu paseo de 15 min hoy??"

                )

            }
            Text(text = "Has tomado $count y subiendo!")
        }

        Row(Modifier.padding(top = 8.dp)) {
            Button(
                onClick = { count++ },
                enabled = count < 10
            ) {
                Text(text = "Añadir agua")
            }
            Button(
                onClick = { count = 0 },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(text = "Limpiar")
            }
        }
    }

   */
    Column(modifier = modifier.padding(16.dp)) {
        var count by remember { mutableStateOf(0) }
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }

    }
    @Composable
    fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
        Column(modifier = modifier.padding(16.dp)) {
            if(count>0){
                Text("You've had $count glasses.")
            }
            Button(onClick = onIncrement, Modifier.padding(top = 8.dp),enabled = count < 10) {
            Text("Añade 1 más")
            }
        }
    }
    @Composable
    fun StatefulCounter(modifier: Modifier = Modifier) {
        var count by remember { mutableStateOf(0) }
        StatelessCounter(count, { count++ }, modifier)
    }
}

