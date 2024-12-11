package net.azarquiel.playasandaluciajpa.model


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.io.Serializable


@Entity(tableName = "costa")
data class Costa(@PrimaryKey
                     @ColumnInfo(name = "id") // nombre columna en tabla
                     var idCosta: Int=0,          // atributo en entity
                     @ColumnInfo(name = "nombre")
                     var nombreCosta:String="",
                     @ColumnInfo(name = "imagen")
                     var imagenCosta:String="",
                     @ColumnInfo(name = "descripcion")
                     var descriptionCosta:String="",

    ) : Serializable


@Entity(tableName = "playa",
    foreignKeys = [ForeignKey(entity = Costa::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("costa"))])
data class Playa(@PrimaryKey
                     @ColumnInfo(name = "id") // nombre en la tabla
                     var idPlaya: Int=0,
                     @ColumnInfo(name = "costa")
                     var costa: Int=0,
                     var azul: Int=0,
                     @ColumnInfo(name = "nombre")
                     var nombrePlaya:String="",
                     @ColumnInfo(name = "imagen")
                     var imagenPlaya:String="",): Serializable


// Relación uno a uno (una playa con una costa)
data class PlayawCosta(
    @Embedded val playa: Playa,
    @Relation(
        parentColumn = "costa",
        entityColumn = "id"
    )
    val costa: Costa
)

    : Serializable