package net.azarquiel.chistesretrofitjpc.model

import java.io.Serializable

data class Usuario (
    var id: Int,
    var nick: String,
    var pass: String
): Serializable

data class Punto (
    var id: Int,
    var idchiste: Int,
    var puntos: Int
)

data class Categoria (
    var id: Int,
    var nombre: String,
): Serializable

data class Chiste (
    var id: Int,
    var idcategoria: Int,
    var contenido: String,
): Serializable


data class Result(
    val categorias: List<Categoria>,
    val chistes: List<Chiste>,
    val usuario: Usuario,
    val chiste: Chiste,
    val punto: Punto,
    val avg: String,
    val msg: String
)
