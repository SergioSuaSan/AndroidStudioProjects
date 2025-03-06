package net.azarquiel.gafasretrofitjpc.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Usuario (
    @SerializedName("id")
    var idusuario: Int,
    var usuario: Int,
    var nick: String,
    var pass: String
): Serializable



data class Comentario (
    var id: Int,
    var gafa: Int,
    @SerializedName("nick")
    var usuario: String,
    var fecha: String,
    var comentario: String,
): Serializable


data class Marca (
    var id: Int,
    var nombre: String,
    var imagen: String
): Serializable

data class Gafa (
    var id: Int,
    var nombre: String,
    var imagen: String,
    var precio: Double,
    var marca: Int
): Serializable



data class Result(
    val marcas: List<Marca>,
    val gafas: List<Gafa>,
    val comentarios: List<Comentario>,
    val usuario: Usuario,
    val usuarios: List<Usuario>,
    val comentario: Comentario,

)

