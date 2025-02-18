package net.azarquiel.parquesnretrofitjpc.model

import java.io.Serializable



data class Parques (
    var id: Int,
    var nombre: String,
    var comunidad: Int,
    var imagen: String,
    var mapa: String,
    var fondo: String,
    var descripcion: String,
    var likes: Int

): Serializable


data class Comunidad (
    var id: Int,
    var nombre: String,
): Serializable

data class Imagen (
    var id: Int,
    var parque: Int,
    var foto: String,
)

data class Result(
    val comunidades: List<Comunidad>,
    val parques: List<Parques>,
    val imagenes: List<Imagen>,
    val msg: String,

)

