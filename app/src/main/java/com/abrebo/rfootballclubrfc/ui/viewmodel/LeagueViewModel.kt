package com.abrebo.rfootballclubrfc.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abrebo.rfootballclubrfc.data.model.Team
import com.abrebo.rfootballclubrfc.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class LeagueViewModel @Inject constructor(var repository: Repository) : ViewModel() {
    var teamList = MutableLiveData<List<Team>>()

    init {
        getAllTeams()
    }
    fun getAllTeams(){
        CoroutineScope(Dispatchers.Main).launch {
            teamList.value=repository.getTeams().sortedWith(compareByDescending {
                it.overall.toInt()
            })
        }
    }
    fun getRandomTeam(filteredTeams: ArrayList<Team>, homeTeam: Team?, awayTeam: Team?): Team {
        var selectedTeam: Team
        do {
            val randomIndex = Random.nextInt(filteredTeams.size)
            selectedTeam = filteredTeams[randomIndex]
        } while (selectedTeam == homeTeam || selectedTeam == awayTeam)

        return selectedTeam
    }
}
