package com.abrebo.rfootballclubrfc.retrofit

import com.abrebo.rfootballclubrfc.data.model.League
import com.abrebo.rfootballclubrfc.data.model.Team
import retrofit2.http.GET

interface TeamDao {
    @GET("leagues_data.json")
    suspend fun getLeagues():List<League>

    @GET("all_teams_overall_with_cups.json")
    suspend fun getTeams():List<Team>




}