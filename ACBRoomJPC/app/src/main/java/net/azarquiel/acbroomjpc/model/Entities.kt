package net.azarquiel.acbroomjpc.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.io.Serializable


@Entity(tableName = "equipo")
data class Equipo(@PrimaryKey
                 var id: Int=0,          // atributo en entity
                 var nombre:String="",
                 var imgestadio:String="",
                 var imgescudo:String="", ): Serializable


@Entity(tableName = "jugador",
    foreignKeys = [ForeignKey(entity = Equipo::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("equipo"))])
data class Jugador(@PrimaryKey
                    var id: Int=0,          // atributo en entity
                    var equipo:Int=0,
                    var nombre:String="",
                    var link:String="",
                    var pais:String="",
                    var estatura:Int=0,
                    var edad:Int=0,
                    var likes:Int=0): Serializable

data class EquipoConJugadores(
    @Embedded val equipo:  Equipo,
    @Relation(
        parentColumn = "id",
        entityColumn = "equipo"
    )
    val tapas: List<Jugador>
): Serializable
