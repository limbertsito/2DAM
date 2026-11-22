package com.example.pruebaexamendi

import androidx.annotation.StringRes

enum class Categoria(
    @StringRes val etiquetaRes: Int
) {
    SALUD(R.string.cat_salud),
    ESTUDIO(R.string.cat_estudio),
    HOGAR(R.string.cat_hogar),
    OCIO(R.string.cat_ocio)
}