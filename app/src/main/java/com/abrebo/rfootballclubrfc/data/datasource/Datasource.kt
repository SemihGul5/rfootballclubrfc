package com.abrebo.rfootballclubrfc.data.datasource

import com.abrebo.rfootballclubrfc.data.model.League
import com.abrebo.rfootballclubrfc.data.model.Team
import com.abrebo.rfootballclubrfc.retrofit.TeamDao
import com.abrebo.rfootballclubrfc.room.DbDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Datasource(var teamDao: TeamDao,var dbDao: DbDao) {

    // api process
    suspend fun getLeagues():List<League> =
        withContext(Dispatchers.IO){
            return@withContext teamDao.getLeagues()
        }

    suspend fun getTeams(): List<Team> =
        withContext(Dispatchers.IO){
            return@withContext teamDao.getTeams()
        }

    suspend fun search(searchText:String):List<Team> = withContext(Dispatchers.IO){
        val searhList=teamDao.getTeams().filter {
            it.team_name.lowercase().contains(searchText.lowercase())
        }
        return@withContext searhList
    }

    // room process
    suspend fun getMyTeams():List<Team> = withContext(Dispatchers.IO){
        return@withContext dbDao.getMyTeams()
    }
    suspend fun addMyTeam(team: Team){
        dbDao.addTeam(team)
    }
    suspend fun deleteMyTeam(team: Team){
        dbDao.deleteTeam(team)
    }


}