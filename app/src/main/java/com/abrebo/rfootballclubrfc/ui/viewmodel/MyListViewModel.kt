package com.abrebo.rfootballclubrfc.ui.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abrebo.rfootballclubrfc.data.model.Team
import com.abrebo.rfootballclubrfc.data.repo.Repository
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random


@HiltViewModel
class MyListViewModel @Inject constructor(var repository: Repository) :ViewModel() {
    var teamList=MutableLiveData<List<Team>>()
    var myTeamList=MutableLiveData<List<Team>>()

    init {
        getAllTeams()
        getMyTeams()
    }

    fun getAllTeams(){
        CoroutineScope(Dispatchers.Main).launch {
            teamList.value=repository.getTeams().sortedWith(compareByDescending{
                it.overall.toInt()
            })
        }
    }

    fun search(searchText:String){
        CoroutineScope(Dispatchers.Main).launch {
            teamList.value=repository.search(searchText).sortedWith(compareByDescending { it.overall.toInt() })
        }
    }
    fun addTeam(team:Team){
        var boolean=false
        myTeamList.value?.forEach {
            if (it.team_image_url==team.team_image_url){
                boolean=true
            }
        }
        if (!boolean){
            viewModelScope.launch {
                repository.addMyTeam(team)
            }
        }
    }
    fun deleteTeam(team: Team){
        viewModelScope.launch {
            repository.deleteMyTeam(team)
            getMyTeams()
        }
    }
    fun getMyTeams(){
        viewModelScope.launch {
            myTeamList.value=repository.getMyTeams().sortedWith(compareByDescending { it.overall.toInt() })
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