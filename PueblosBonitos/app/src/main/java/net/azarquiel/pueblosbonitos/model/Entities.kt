package net.azarquiel.pueblosbonitos.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.io.Serializable


@Entity(tableName = "omunidad")
data class Comunidad(@PrimaryKey
                 @ColumnInfo(name = "id") // nombre columna en tabla
                 var id: Int=0,          // atributo en entity
                 @ColumnInfo(name = "nombre")
                 var nombre:String="",
) : Serializable


@Entity(tableName = "Provincia",
    foreignKeys = [ForeignKey(entity = Comunidad::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("comunidad"))])
data class Provincia(@PrimaryKey
                    @ColumnInfo(name = "id") // nombre en la tabla
                    var id: Int=0,          // atributo en entity
                    @ColumnInfo(name = "nombre")
                    var nombre:String="",
                    @ColumnInfo(name = "comunidad")
                    var comunidad:Int=0): Serializable

@Entity(tableName = "Pueblo",
    foreignKeys = [ForeignKey(entity = Comunidad::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("provincia"))])
data class Pueblo(@PrimaryKey
                     @ColumnInfo(name = "id") // nombre en la tabla
                     var id: Int=0,          // atributo en entity
                     @ColumnInfo(name = "nombre")
                     var nombre:String="",
                     @ColumnInfo(name = "imagen")
                     var imagen:String="",
                     @ColumnInfo(name = "link")
                     var link:String="",
                     @ColumnInfo(name = "provincia")
                     var provincia:Int=0,
                     @ColumnInfo(name = "fav")
                     var fav:Int=0): Serializable

// Relación uno a muchos (una provincia con muchos pueblos)
data class Provinciawp(
    @Embedded val provincia: Provincia,
    @Relation(
        parentColumn = "id",
        entityColumn = "provincia"
    )
    val pueblos: List<Pueblo>
): Serializable

// Relación uno a uno (un pueblo con una provincia)
data class Pueblowp(
    @Embedded val pueblo: Pueblo,
    @Relation(
        parentColumn = "provincia",
        entityColumn = "id"
    )
    val provincia: Provincia
)
