package vb.progra.ejercicioentregableprogramacin

import androidx.compose.ui.input.key.Key.Companion.I

class Calculadora {
    fun calcularIva(cantidad: Double, baseImponible: Double): Double {
        val IVA = cantidad - baseImponible
        return (Math.round(IVA * 100.0) / 100.0)

    }

    fun calcularBaseImponible(cantidad: Double, iva: Double): Double {
        val baseImponible = cantidad / (1 + iva / 100)
        return (Math.round(baseImponible * 100.0) / 100.0)
    }
}
