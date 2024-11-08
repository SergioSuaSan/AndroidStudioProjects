package net.azarquiel.metroroomjpc.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "linea")
data class Linea(@PrimaryKey
                 var id: Int?=0,          // atributo en entity
                 var nombre:String="",
                 var color:String="")

@Entity(tableName = "estacion",
    foreignKeys = [ForeignKey(entity = Linea::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("linea"))])
data class Estacion(@PrimaryKey
                    var id: Int?=0,          // atributo en entity
                    var nombre:String="",
                    var linea:Int=0)