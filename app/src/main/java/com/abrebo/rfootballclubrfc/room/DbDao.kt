package com.abrebo.rfootballclubrfc.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.abrebo.rfootballclubrfc.data.model.Team

@Dao
interface DbDao {
    @Query("select * from Teams")
    suspend fun getMyTeams():List<Team>

    @Insert
    suspend fun addTeam(team: Team)

    @Delete
    suspend fun deleteTeam(team: Team)

    @Insert
    suspend fun addTeams(teams: List<Team>)

    @Delete
    suspend fun deleteTeams(teams: List<Team>)
}