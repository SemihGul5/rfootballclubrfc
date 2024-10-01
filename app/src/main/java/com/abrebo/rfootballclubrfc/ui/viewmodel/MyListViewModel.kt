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
        viewModelScope.launch {
            repository.addMyTeam(team)
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

}