package com.abrebo.rfootballclubrfc.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abrebo.rfootballclubrfc.data.model.Team

@Database(entities = [Team::class], version = 1)
abstract class DbPlayer1 : RoomDatabase() {
    abstract fun getDao(): DbDao

    companion object {
        @Volatile private var INSTANCE: DbPlayer1? = null

        fun getInstance(context: Context): DbPlayer1 {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DbPlayer1::class.java,
                    "teams_player1.sqlite"
                ).createFromAsset("teams.sqlite")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
