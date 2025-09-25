package com.abrebo.rfootballclubrfc.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.abrebo.rfootballclubrfc.R
import com.abrebo.rfootballclubrfc.data.model.Team
import com.abrebo.rfootballclubrfc.databinding.FragmentMyTeamListBinding
import com.abrebo.rfootballclubrfc.ui.adapter.MyListAdapter
import com.abrebo.rfootballclubrfc.ui.adapter.RandomTeamAdapter
import com.abrebo.rfootballclubrfc.ui.viewmodel.MyListViewModel
import com.abrebo.rfootballclubrfc.util.PageType
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyTeamListFragment : Fragment() {
    private lateinit var binding:FragmentMyTeamListBinding
    private lateinit var viewModel:MyListViewModel
    private lateinit var randomTeamAdapter: RandomTeamAdapter
    private var randomTeams=ArrayList<Team>()
    private lateinit var filteredTeams:List<Team>
    private var homeTeam: Team?=null
    private var awayTeam: Team?=null
    private lateinit var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp:MyListViewModel by viewModels()
        viewModel=temp
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=FragmentMyTeamListBinding.inflate(inflater, container, false)
        filteredTeams=ArrayList<Team>()
        // Initialize Mobile Ads SDK
        MobileAds.initialize(requireContext()) {}
        // Setup Banner Ad
        adView = AdView(requireContext())
        adView.adUnitId = requireContext().getString(R.string.banner_ad_unit_id_my_team_list_fragment)
        adView.setAdSize(AdSize.BANNER)
        binding.adView.removeAllViews()
        binding.adView.addView(adView)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        // Load Interstitial Ad
        viewModel.loadInterstitialAd()

        //observe team list
        viewModel.myTeamList.observe(viewLifecycleOwner){
            filteredTeams = ArrayList(it)
            binding.countText.text ="Takım sayısı: "+ it.size.toString()
            val adapter=MyListAdapter(requireContext(),it,viewModel,PageType.MYLIST,PageType.MAIN)
            binding.recyclerViewMyTeamList.adapter=adapter
        }
        randomTeamAdapter=RandomTeamAdapter(requireContext(),randomTeams)
        binding.recyclerViewPredict.adapter=randomTeamAdapter


        binding.buttonHome.setOnClickListener {
            addRandomTeam(true)
        }
        binding.buttonAway.setOnClickListener {
            addRandomTeam(false)
        }
        binding.imageViewBack.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_myTeamListFragment_to_mainFragment)
        }





        return binding.root
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun addRandomTeam(isHomeTeam: Boolean) {
        if (filteredTeams.size < 2) {
            Snackbar.make(requireView(), "Rastgele takım seçmeye gerek yok!", Snackbar.LENGTH_SHORT).show()
            return
        }

        val newTeam = viewModel.getRandomTeam(filteredTeams as ArrayList<Team>, homeTeam, awayTeam)

        if (newTeam == null) {
            Snackbar.make(requireView(), "Seçilebilecek başka takım kalmadı!", Snackbar.LENGTH_SHORT).show()
            return
        }

        if (isHomeTeam) {
            homeTeam = newTeam
        } else {
            awayTeam = newTeam
        }

        randomTeams.clear()
        homeTeam?.let { randomTeams.add(it) }
        awayTeam?.let { randomTeams.add(it) }

        randomTeamAdapter.notifyDataSetChanged()
    }


}