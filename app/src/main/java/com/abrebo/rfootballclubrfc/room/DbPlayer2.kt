package com.abrebo.rfootballclubrfc.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abrebo.rfootballclubrfc.data.model.Team

@Database(entities = [Team::class], version = 1)
abstract class DbPlayer2 : RoomDatabase() {
    abstract fun getDao(): DbDao

    companion object {
        @Volatile private var INSTANCE: DbPlayer2? = null

        fun getInstance(context: Context): DbPlayer2 {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DbPlayer2::class.java,
                    "teams_player2.sqlite"
                ).createFromAsset("teams.sqlite")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
