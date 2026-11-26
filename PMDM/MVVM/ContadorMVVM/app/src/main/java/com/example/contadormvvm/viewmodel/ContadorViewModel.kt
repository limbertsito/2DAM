package com.example.contadormvvm.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.contadormvvm.ui.ContadorUiState
import com.example.contadormvvm.utils.MAX
import com.example.contadormvvm.utils.MIN

//Constantes

class ContadorViewModel: ViewModel() {
    //Creamos la variable para poder acceder a tevaes de ella a los datos
    var uiState by mutableStateOf(ContadorUiState())
        private set

    fun incrementar(){
        if (uiState.contador<MAX) {
            //Le cambiamos el valor de estado a la variable de estado que hemos declarado
            uiState = uiState.copy(
                //Creamso una copia porque no ons deja porque es inmutbale
                //Creamso la copia del estado para poder alterarlo
                contador = uiState.contador +1,
                numMovimientos = uiState.numMovimientos+1
            )
        }
    }
    fun decrementar(){
        if (uiState.contador>MIN) {
            uiState =  uiState.copy(
                contador = uiState.contador-1,
                numMovimientos = uiState.numMovimientos+1
            )
        }
    }
    fun reiniciar(){
        //Creamso el objeto de nuevo como estan inicalizados a 0

        uiState= ContadorUiState()
    //        uiState.copy(contador = 0,
//            numMovimientos = 0
//            )
    }
}