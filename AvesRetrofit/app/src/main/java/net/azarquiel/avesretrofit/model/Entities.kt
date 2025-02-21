package net.azarquiel.avesretrofit.model

import java.io.Serializable
import java.util.Date

data class Usuario (
    var idusuario: Int,
    var nick: String,
    var pass: String
): Serializable

data class Recurso (
    var id: Int,
    var zona: Int,
    var titulo: String,
    var url: String,
): Serializable

data class Comentario (
    var id: Int,
    var usuario: Int,
    var recurso: Int,
    var fecha: Date,
    var comentario: String,
): Serializable


data class Zona (
    var id: Int,
    var nombre: String,
    var localizacion: String,
    var geom_lat: Double,
    var geom_lon: Double,
    var formaciones_principales: String,
    var presentacion: String
): Serializable



data class Result(

    val zonas: List<Zona>,
    val recursos: List<Recurso>,
    val comentarios: List<Comentario>,
    val usuario: Usuario,
    val avg: String,
    val msg: String
)

