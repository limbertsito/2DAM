package com.example.contadormvvm.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contadormvvm.utils.MAX
import com.example.contadormvvm.utils.MIN
import com.example.contadormvvm.viewmodel.ContadorViewModel


@Composable
fun Contador(
    estado : ContadorUiState,
    onIncrementar:() -> Unit,
    onDecrementar:() ->Unit,
    onReiniciar:() -> Unit,

){
    Box(modifier = Modifier.fillMaxSize()){
        Text("Movimientos : $numMov",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopEnd)  )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Contador : $contador",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onIncrementar,
                enabled = contador<MAX
            ) {
                Text("Incrementar")
            }
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onDecrementar,
                enabled = contador> MIN
            ) {
                Text("Decrementar")
            }
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onReiniciar
            ) {
                Text("Reiniciar")
            }
    }


    }
}

@Composable
fun PantallaContador(
    //Aqui metemos el estadp
    viewModel: ContadorViewModel = viewModel()
){
    Contador(
        //le decimos al contador qeu escuche el viewmodel y su variable
        contador = viewModel.uiState,
        onIncrementar = {viewModel.incrementar()}, //SE PUEDE ESCRIBIR
        onDecrementar =viewModel::decrementar, //DE AMBAS MANERAS ya no es una lambda
        onReiniciar = viewModel::reiniciar,
     //  numMov = viewModel.uiState
        //Estamos igualnando la funcion diciendole onDecrementar es igual a viewmodel Decrementar
        //y el la de arriba le decimso que haga x funcion.
    )
}

