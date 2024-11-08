package net.azarquiel.famososroomjpc.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "Categoria")
data class Categoria(@PrimaryKey
                 var c_id: Int=0,
                 var c_name_es:String="")

@Entity(tableName = "Famoso",
    foreignKeys = [ForeignKey(entity = Categoria::class,
        parentColumns = arrayOf("c_id"),
        childColumns = arrayOf("c_id"))])
data class Famoso(@PrimaryKey
                    var p_id: Int=0,
                    var c_id: Int=0,
                    var p_name_es:String="")