package com.abrebo.rfootballclubrfc.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.abrebo.rfootballclubrfc.R
import com.abrebo.rfootballclubrfc.databinding.FragmentMainBinding
import com.abrebo.rfootballclubrfc.ui.adapter.LeagueAdapter
import com.abrebo.rfootballclubrfc.ui.viewmodel.MainPageViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainPageViewModel
    private lateinit var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: MainPageViewModel by viewModels()
        viewModel = temp
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)

        // Initialize Mobile Ads SDK
        MobileAds.initialize(requireContext()) {}

        // Setup Banner Ad
        adView = AdView(requireContext())
        adView.adUnitId = requireContext().getString(R.string.banner_ad_unit_id_main_fragment)
        adView.setAdSize(AdSize.BANNER)
        binding.adView.removeAllViews()
        binding.adView.addView(adView)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        // Load Interstitial Ad
        viewModel.loadInterstitialAd()
        binding.fabAddToList.setOnClickListener { fabAddListClicked(it) }
        binding.fabMyList.setOnClickListener { fabMyListClicked(it) }
        // Observe the league list
        viewModel.leagueList.observe(viewLifecycleOwner) { leagueList ->
            val adapter = LeagueAdapter(requireContext(), leagueList, viewModel.interstitialAd,requireActivity())
            binding.recyclerView.adapter = adapter
        }





        return binding.root
    }
    private fun fabMyListClicked(view:View){
        viewModel.showInterstitialAd(requireActivity())
        Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_myTeamListFragment)
    }


    private fun fabAddListClicked(view: View) {
        viewModel.showInterstitialAd(requireActivity())
        Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_addListFragment)
    }
}

