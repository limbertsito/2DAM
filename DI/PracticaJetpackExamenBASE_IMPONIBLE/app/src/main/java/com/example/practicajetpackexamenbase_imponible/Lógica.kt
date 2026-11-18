package com.example.practicajetpackexamenbase_imponible

import androidx.compose.runtime.Composable


fun calcularIva(cantidad : Double): Double{
    val iva=21
    return ((cantidad*iva)/100)
}