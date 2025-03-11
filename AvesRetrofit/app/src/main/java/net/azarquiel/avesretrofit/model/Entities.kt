package net.azarquiel.avesretrofit.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.Date

data class Usuario (
    @SerializedName("id")
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
    @SerializedName("nick")
    var usuario: String,
    var recurso: Int,
    var fecha: String,
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
    val usuarios: List<Usuario>,
    val comentario: Comentario,
    /*
    val avg: String,
    val msg: String
     */
)

