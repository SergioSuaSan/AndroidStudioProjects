package net.azarquiel.metroroomjpc.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.io.Serializable


@Entity(tableName = "linea")
data class Linea(@PrimaryKey
                 var id: Int?=0,          // atributo en entity
                 var nombre:String="",
                 var color:String=""): Serializable

@Entity(tableName = "estacion",
    foreignKeys = [ForeignKey(entity = Linea::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("linea"))])
data class Estacion(@PrimaryKey
                    var id: Int?=0,          // atributo en entity
                    var nombre:String="",
                    var linea:Int=0): Serializable

// Relación uno a muchos (un linea => muchas estaciones)
data class LineaWithEstaciones(
    @Embedded val linea: Linea,
    @Relation(
        parentColumn = "id",
        entityColumn = "linea"
    )
    val estaciones: List<Estacion>
): Serializable

