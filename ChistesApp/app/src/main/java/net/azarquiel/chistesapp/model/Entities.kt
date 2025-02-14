package net.azarquiel.chistesapp.model

import java.io.Serializable

data class Usuario (
    var id: String,
    var nick: String,
    var pass: String
): Serializable

data class Categoria (
    var id:String,
    var nombre: String
): Serializable
data class Chiste (
    var id:String,
    var idcategoria: String,
    var contenido:String
):Serializable
data class Punto (
    var id:String,
    var idchiste: String,
    var puntos:String
):Serializable

data class Result(
    val categorias: List<Categoria>,
    val chistes: List<Chiste>,
    val usuario: Usuario,
    val chiste:Chiste,
    val punto:Punto,
    val avg:String
)
