package com.abrebo.rfootballclubrfc.data.repo

import com.abrebo.rfootballclubrfc.data.datasource.Datasource
import com.abrebo.rfootballclubrfc.data.model.League
import com.abrebo.rfootballclubrfc.data.model.Team

class Repository(var datasource: Datasource) {
    suspend fun getLeagues():List<League> = datasource.getLeagues()
    suspend fun getTeams(): List<Team> =datasource.getTeams()
    suspend fun search(searchText:String):List<Team> = datasource.search(searchText)



}