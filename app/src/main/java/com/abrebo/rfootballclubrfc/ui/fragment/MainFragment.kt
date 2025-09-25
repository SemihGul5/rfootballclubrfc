package com.abrebo.rfootballclubrfc.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.abrebo.rfootballclubrfc.R
import com.abrebo.rfootballclubrfc.databinding.FragmentMainBinding
import com.abrebo.rfootballclubrfc.ui.adapter.LeagueAdapter
import com.abrebo.rfootballclubrfc.ui.viewmodel.MainPageViewModel
import com.abrebo.rfootballclubrfc.util.PageType
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
    private lateinit var adViewTop: AdView
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

        // top ad view
        adViewTop = AdView(requireContext())
        adViewTop.adUnitId = "ca-app-pub-4667560937795938/9380128541"
        adViewTop.setAdSize(AdSize.BANNER)
        binding.adViewTop.removeAllViews()
        binding.adViewTop.addView(adViewTop)

        val adRequesttop = AdRequest.Builder().build()
        adViewTop.loadAd(adRequesttop)

        // Load Interstitial Ad
        viewModel.loadInterstitialAd()
        binding.fabAddToList.setOnClickListener { fabAddListClicked(it) }
        binding.fabMyList.setOnClickListener { fabMyListClicked(it) }
        // Observe the league list
        viewModel.leagueList.observe(viewLifecycleOwner) { leagueList ->
            val adapter = LeagueAdapter(requireContext(), leagueList, viewModel.interstitialAd, requireActivity())

            val gridLayoutManager = GridLayoutManager(requireContext(), 2)
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    // Reklam en sonda, tam satırı kaplasın
                    return if (position == leagueList.size) 2 else 1
                }
            }

            binding.recyclerView.layoutManager = gridLayoutManager
            binding.recyclerView.adapter = adapter
        }


        /*binding.button.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_turnuvaOlusturFragment)
        }*/




        return binding.root
    }
    private fun fabMyListClicked(view:View){
        viewModel.showInterstitialAd(requireActivity())
        Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_myTeamListFragment)
    }


    private fun fabAddListClicked(view: View) {
        viewModel.showInterstitialAd(requireActivity())
        val navd=MainFragmentDirections.actionMainFragmentToAddListFragment(PageType.MAIN)
        Navigation.findNavController(view).navigate(navd)
    }


}

