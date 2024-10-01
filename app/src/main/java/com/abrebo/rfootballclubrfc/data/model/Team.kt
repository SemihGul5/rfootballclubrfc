package com.abrebo.rfootballclubrfc.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "Teams")
data class Team(@ColumnInfo(name = "team_name") var team_name:String,
                @ColumnInfo(name = "overall") var overall:String,
                @ColumnInfo(name = "league_name") var league_name:String,
                @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "team_image_url") var team_image_url:String,
                @ColumnInfo(name = "country_name") var country_name:String,
                @ColumnInfo(name = "european_cup") var european_cup:String,
                @ColumnInfo(name = "stars") var stars:Double):Serializable {
}