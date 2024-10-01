package com.abrebo.rfootballclubrfc.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abrebo.rfootballclubrfc.data.model.Team

@Database(entities = [Team::class], version = 1)
abstract class Db:RoomDatabase() {
    abstract fun getDbDao():DbDao
}