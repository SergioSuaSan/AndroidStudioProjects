package net.azarquiel.playasandaluciajpa.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.azarquiel.playasandaluciajpa.daos.DaoCosta
import net.azarquiel.playasandaluciajpa.daos.DaoPlaya

@Database(entities = [Costa::class, Playa::class], version = 1)
abstract class PlayaAndaluDB : RoomDatabase() {
    abstract fun daoCosta(): DaoCosta
    abstract fun daoPlaya(): DaoPlaya
    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: PlayaAndaluDB? = null
        fun getDatabase(context: Context): PlayaAndaluDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlayaAndaluDB::class.java, "playasandalu.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}