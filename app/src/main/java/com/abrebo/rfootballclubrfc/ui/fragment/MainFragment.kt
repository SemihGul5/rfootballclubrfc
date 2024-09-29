package com.abrebo.rfootballclubrfc.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.abrebo.rfootballclubrfc.R
import com.abrebo.rfootballclubrfc.databinding.FragmentMainBinding
import com.abrebo.rfootballclubrfc.ui.adapter.LeagueAdapter
import com.abrebo.rfootballclubrfc.ui.viewmodel.MainPageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding:FragmentMainBinding
    private lateinit var viewModel:MainPageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp:MainPageViewModel by viewModels()
        viewModel=temp
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=FragmentMainBinding.inflate(inflater, container, false)

        viewModel.leagueList.observe(viewLifecycleOwner){
            val adapter=LeagueAdapter(requireContext(),it)
            binding.recyclerView.adapter=adapter
        }




        return binding.root
    }

}