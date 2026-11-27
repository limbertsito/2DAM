package com.example.laboratoriodecarteles.ui

import android.R

data class CarteUiState(
    val tituloCartel : String  = "",
    val ImagenFondo : FondoValor = FondoValor.INDIGO,
    val fecha : Boolean = false,

)
enum class FondoValor {
    INDIGO,TERRACOTA
}

