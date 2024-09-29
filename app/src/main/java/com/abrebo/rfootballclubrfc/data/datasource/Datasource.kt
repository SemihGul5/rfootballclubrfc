package com.abrebo.rfootballclubrfc.data.datasource

import com.abrebo.rfootballclubrfc.data.model.League
import com.abrebo.rfootballclubrfc.data.model.Team
import com.abrebo.rfootballclubrfc.retrofit.TeamDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Datasource(var teamDao: TeamDao) {

    suspend fun getLeagues():List<League> =
        withContext(Dispatchers.IO){
            return@withContext teamDao.getLeagues()
        }

    suspend fun getTeams(): List<Team> =
        withContext(Dispatchers.IO){
            return@withContext teamDao.getTeams()
        }


}