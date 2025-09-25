package com.abrebo.rfootballclubrfc.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abrebo.rfootballclubrfc.R
import com.abrebo.rfootballclubrfc.databinding.FragmentAddListBinding
import com.abrebo.rfootballclubrfc.ui.adapter.MyListAdapter
import com.abrebo.rfootballclubrfc.ui.viewmodel.MyListViewModel
import com.abrebo.rfootballclubrfc.util.PageType
import com.google.android.gms.ads.*

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddListFragment : Fragment() {

    private var _binding: FragmentAddListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MyListViewModel by viewModels()
    private lateinit var adView: AdView

    private lateinit var bundle:PageType
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddListBinding.inflate(inflater, container, false)
        bundle=AddListFragmentArgs.fromBundle(requireArguments()).pageType
        setupAds()
        observeViewModel()
        setupUIListeners()

        return binding.root
    }

    private fun setupAds() {
        MobileAds.initialize(requireContext())

        adView = AdView(requireContext()).apply {
            adUnitId = getString(R.string.banner_ad_unit_id_add_list_fragment)
            setAdSize(AdSize.BANNER)
        }
        binding.adView.apply {
            removeAllViews()
            addView(adView)
        }
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        viewModel.loadInterstitialAd()
    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        viewModel.teamList.observe(viewLifecycleOwner) { teamList ->
            binding.countText.text="Takım sayısı: "+ teamList.size.toString()
            val adapter = MyListAdapter(
                mContext = requireContext(),
                teamList = teamList,
                viewModel = viewModel,
                page = PageType.ADDLIST,
                pageType2 = bundle

            )
            binding.recyclerViewAddList.adapter = adapter
        }
    }

    private fun setupUIListeners() {
        binding.imageViewBack.setOnClickListener {
            findNavController().navigate(R.id.action_addListFragment_to_mainFragment)
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim()
                if (query.isNotEmpty()) {
                    viewModel.search(query)
                } else {
                    viewModel.getAllTeams()
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
