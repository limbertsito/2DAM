package com.example.pruebaexamendi

data class Habito(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val realizado: Boolean,
    val categoria: Categoria
)