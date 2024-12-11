package net.azarquiel.examenplantilla.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "linea")
data class Linea(@PrimaryKey
                 @ColumnInfo(name = "id") // nombre columna en tabla
                 var id: Int?=0,          // atributo en entity
                 @ColumnInfo(name = "nombre")
                 var nombre:String="",
                 @ColumnInfo(name = "color")
                 var color:String=""): Serializable


@Entity(tableName = "estacion",
    foreignKeys = [ForeignKey(entity = Linea::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("linea"))])
data class Estacion(@PrimaryKey
                    @ColumnInfo(name = "id") // nombre en la tabla
                    var id: Int?=0,          // atributo en entity
                    @ColumnInfo(name = "nombre")
                    var nombre:String="",
                    @ColumnInfo(name = "linea")
                    var linea:Int=0): Serializable
//@Transaction
