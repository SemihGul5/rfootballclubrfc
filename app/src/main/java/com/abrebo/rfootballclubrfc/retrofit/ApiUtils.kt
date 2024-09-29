package com.abrebo.rfootballclubrfc.retrofit

class ApiUtils {
    companion object{
        val BASE_URL="https://raw.githubusercontent.com/SemihGul5/Sofifa-Data-Fetcher/refs/heads/main/"
        fun getTeamDao():TeamDao{
            return RetrofitClient.getClient(BASE_URL).create(TeamDao::class.java)
        }
    }
}