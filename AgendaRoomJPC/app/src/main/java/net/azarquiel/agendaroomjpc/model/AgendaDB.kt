package net.azarquiel.agendaroomjpc.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Amigo::class], version = 1)
abstract class Agendadb: RoomDatabase() {
    abstract fun AmigoDao(): AmigoDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE:  Agendadb? = null


        fun getDatabase(context: Context): Agendadb {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Agendadb::class.java,   "Agendadb.sqlite"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
