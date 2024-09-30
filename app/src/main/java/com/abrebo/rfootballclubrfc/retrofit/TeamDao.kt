package com.abrebo.rfootballclubrfc.retrofit

import com.abrebo.rfootballclubrfc.data.model.League
import com.abrebo.rfootballclubrfc.data.model.Team
import retrofit2.http.GET

interface TeamDao {
    @GET("leagues_data_for_app.json")
    suspend fun getLeagues():List<League>

    @GET("teams.json")
    suspend fun getTeams():List<Team>




}