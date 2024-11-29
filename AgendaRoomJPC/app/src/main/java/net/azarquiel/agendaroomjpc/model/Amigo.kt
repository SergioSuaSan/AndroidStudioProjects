package net.azarquiel.agendaroomjpc.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "amigo")
data class Amigo(@PrimaryKey
                 @ColumnInfo(name = "id") // nombre columna en tabla
                 var id: Int?=0,            // atributo en entity
                 var nombre:String="",
                 var email:String="",
                 var telefono:String="")
