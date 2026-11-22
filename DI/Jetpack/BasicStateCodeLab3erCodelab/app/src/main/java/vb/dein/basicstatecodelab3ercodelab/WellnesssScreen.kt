package vb.dein.basicstatecodelab3ercodelab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun WellnesScreen(modifier: Modifier = Modifier) {
    StatefulCounter(modifier)

}

@Composable
fun StatefulCounter(x0: Modifier) {
    var count by remember { mutableStateOf(0) }
    //StatelessCounter(count, { count++ }, modifier )
}


