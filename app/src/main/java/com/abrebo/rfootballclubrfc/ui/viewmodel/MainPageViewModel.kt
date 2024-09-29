package com.abrebo.rfootballclubrfc.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abrebo.rfootballclubrfc.data.model.League
import com.abrebo.rfootballclubrfc.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainPageViewModel @Inject constructor(var repository: Repository) :ViewModel() {
    var leagueList=MutableLiveData<List<League>>()

    init {
        getLeague()
    }

    fun getLeague(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                leagueList.value=repository.getLeagues()
            }catch (e:Exception){
                Log.e("error",e.toString())
            }
        }
    }

}