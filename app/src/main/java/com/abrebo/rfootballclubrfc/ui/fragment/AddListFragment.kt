package com.abrebo.rfootballclubrfc.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.abrebo.rfootballclubrfc.R
import com.abrebo.rfootballclubrfc.databinding.FragmentAddListBinding
import com.abrebo.rfootballclubrfc.ui.adapter.MyListAdapter
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddListFragment : Fragment() {
    private lateinit var binding:FragmentAddListBinding
    private lateinit var viewModel:MyListViewModel
    private lateinit var adView: AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp:MyListViewModel by viewModels()
        viewModel=temp
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=FragmentAddListBinding.inflate(inflater, container, false)
        // Initialize Mobile Ads SDK
        MobileAds.initialize(requireContext()) {}
        // Setup Banner Ad
        adView = AdView(requireContext())
        adView.adUnitId = requireContext().getString(R.string.banner_ad_unit_id_add_list_fragment)
        adView.setAdSize(AdSize.BANNER)
        binding.adView.removeAllViews()
        binding.adView.addView(adView)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        // Load Interstitial Ad
        viewModel.loadInterstitialAd()

        //observe team list
        viewModel.teamList.observe(viewLifecycleOwner){
            val adapter=MyListAdapter(requireContext(),it,viewModel,PageType.ADDLIST)
            binding.recyclerViewAddList.adapter=adapter
        }

        binding.imageViewBack.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_addListFragment_to_mainFragment)
        }

        binding.searchEditText.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().isNotEmpty()){
                    val searchText= p0.toString().trim()
                    viewModel.search(searchText)
                }else{
                    viewModel.getAllTeams()
                }
            }
            override fun afterTextChanged(p0: Editable?) {}

        })




        return binding.root
    }

}