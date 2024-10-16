package com.abrebo.rfootballclubrfc.ui.viewmodel

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abrebo.rfootballclubrfc.R
import com.abrebo.rfootballclubrfc.data.model.League
import com.abrebo.rfootballclubrfc.data.repo.Repository
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainPageViewModel @Inject constructor(var repository: Repository,application: Application): AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext
    var leagueList=MutableLiveData<List<League>>()
    var interstitialAd: InterstitialAd? = null

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
    fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(context, context.getString(R.string.interstitial_ad_unit_id_all), adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    interstitialAd = null
                }
            })
    }
    fun showInterstitialAd(activity: Activity) {
        if (interstitialAd!=null){
            interstitialAd?.show(activity)
        }
    }
}