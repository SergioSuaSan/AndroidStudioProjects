package net.azarquiel.pueblosbonitos.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.azarquiel.pueblosbonitos.daos.DaoComunidad
import net.azarquiel.pueblosbonitos.daos.DaoPueblo

@Database(entities = [Comunidad::class, Provincia::class, Pueblo::class], version = 1)
abstract class PueblosBonitosDB : RoomDatabase() {
    abstract fun daoComunidad(): DaoComunidad
    abstract fun daoPueblo(): DaoPueblo
    companion object {
// Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: PueblosBonitosDB? = null
        fun getDatabase(context: Context): PueblosBonitosDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PueblosBonitosDB::class.java, "pueblosbonitos.sqlite"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}