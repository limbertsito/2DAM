package com.example.practicaexamenjetpackpropinas

fun calcularPropina(cantidad: Double, porcentaje: Double = 15.00, redondeo: Boolean): Double{
    var propina = (cantidad*porcentaje)/100
    if(redondeo){
        propina = Math.ceil(propina)
    }
     return propina
}