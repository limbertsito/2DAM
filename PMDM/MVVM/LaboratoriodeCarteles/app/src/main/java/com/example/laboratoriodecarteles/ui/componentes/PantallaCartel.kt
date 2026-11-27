package com.example.laboratoriodecarteles.ui.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.laboratoriodecarteles.ui.CarteUiState
import com.example.laboratoriodecarteles.view_model.CartelesViewModel

@Composable
fun PantallaCartel (
    viewModel: CartelesViewModel = viewModel()
){
    Scaffold(
        topBar = {TopAppBar()}
    ){
        innerPadding->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            OutlinedTextField(
                state = rememberTextFieldState(),
                label = { Text("Titulo de cartel") }
            )
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier.padding(24.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

            }
        }
    }
}