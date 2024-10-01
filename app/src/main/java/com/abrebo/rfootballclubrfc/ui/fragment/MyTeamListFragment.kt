package com.abrebo.rfootballclubrfc.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.abrebo.rfootballclubrfc.R
import com.abrebo.rfootballclubrfc.databinding.FragmentMyTeamListBinding
import com.abrebo.rfootballclubrfc.ui.adapter.MyListAdapter
import com.abrebo.rfootballclubrfc.ui.viewmodel.MyListViewModel
import com.abrebo.rfootballclubrfc.util.PageType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyTeamListFragment : Fragment() {
    private lateinit var binding:FragmentMyTeamListBinding
    private lateinit var viewModel:MyListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp:MyListViewModel by viewModels()
        viewModel=temp
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=FragmentMyTeamListBinding.inflate(inflater, container, false)

        viewModel.myTeamList.observe(viewLifecycleOwner){
            val adapter=MyListAdapter(requireContext(),it,viewModel,PageType.MYLIST)
            binding.recyclerViewMyTeamList.adapter=adapter
        }







        return binding.root
    }

}