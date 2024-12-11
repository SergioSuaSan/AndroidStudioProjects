package net.azarquiel.examenplantilla.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//enumeradas todas las tablas (las del entities)
@Database(entities = [Linea::class, Estacion::class], version = 1)
abstract class DB: RoomDatabase() {
    //UNA MENCION POR CADA DAO
//   abstract fun lineaDAO(): LineaDAO
//   abstract fun estacionDAO(): EstacionDAO
   companion object { //RECUERDA CAMBIAR LAS INSTANCIAS DE LA BBDD
       // Singleton prevents multiple instances of database opening at the same time.
       @Volatile
       private var INSTANCE:  DB? = null //DEPENDE DE COMO SE LLAME EL ARCHIVO



       fun getDatabase(context: Context): DB {
           val tempInstance = INSTANCE
           if (tempInstance != null) {
               return tempInstance
           }
           synchronized(this) {
               val instance = Room.databaseBuilder(
                   context.applicationContext,
                   DB::class.java,   "MetroDB.db" //CAMBIAR NOMBRE DE LA BBDD
               ).build()
               INSTANCE = instance
               return instance
           }
       }
   }
}
