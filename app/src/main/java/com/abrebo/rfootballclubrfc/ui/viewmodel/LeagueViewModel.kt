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

}
