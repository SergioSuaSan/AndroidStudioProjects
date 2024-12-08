package net.azarquiel.pueblosbonitos.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.io.Serializable


@Entity(tableName = "comunidad")
data class Comunidad(@PrimaryKey
                 @ColumnInfo(name = "id") // nombre columna en tabla
                 var idComunidad: Int=0,          // atributo en entity
                 @ColumnInfo(name = "nombre")
                 var nombreComunidad:String="",) : Serializable


@Entity(tableName = "provincia",
    foreignKeys = [ForeignKey(entity = Comunidad::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("comunidad"))])
data class Provincia(@PrimaryKey
                    @ColumnInfo(name = "id") // nombre en la tabla
                    var idProvincia: Int=0,          // atributo en entity
                    @ColumnInfo(name = "nombre")
                    var nombreProvincia:String="",
                    var comunidad:Int=0): Serializable

@Entity(tableName = "pueblo",
    foreignKeys = [ForeignKey(entity = Provincia::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("provincia"))])
data class Pueblo(@PrimaryKey
                     @ColumnInfo(name = "id") // nombre en la tabla
                     var idPueblo: Int=0,          // atributo en entity
                     @ColumnInfo(name = "nombre")
                     var nombrePueblo:String="",
                     var imagen:String="",
                     var link:String="",
                     var provincia:Int=0,
                     var fav:Int=0): Serializable

/*
// Relación uno a muchos (una provincia con muchos pueblos)
data class Provinciawp(
    @Embedded val provincia: Provincia,
    @Relation(
        parentColumn = "id",
        entityColumn = "provincia"
    )
    val pueblos: List<Pueblo>
): Serializable
 */
// Relación uno a uno (un pueblo con una provincia)
data class Pueblowp(
    @Embedded val pueblo: Pueblo,
    @Relation(
        parentColumn = "provincia",
        entityColumn = "id"
    )
    val provincia: Provincia
)

    : Serializable