package com.abrebo.rfootballclubrfc.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.abrebo.rfootballclubrfc.R
import com.abrebo.rfootballclubrfc.data.model.Team
import com.abrebo.rfootballclubrfc.databinding.FragmentAddListBinding
import com.abrebo.rfootballclubrfc.ui.adapter.AddListAdapter
import com.abrebo.rfootballclubrfc.ui.viewmodel.AddListViewModel
import com.abrebo.rfootballclubrfc.ui.viewmodel.LeagueViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddListFragment : Fragment() {
    private lateinit var binding:FragmentAddListBinding
    private lateinit var viewModel:AddListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp:AddListViewModel by viewModels()
        viewModel=temp
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=FragmentAddListBinding.inflate(inflater, container, false)

        viewModel.teamList.observe(viewLifecycleOwner){
            val adapter=AddListAdapter(requireContext(),it)
            binding.recyclerViewAddList.adapter=adapter
        }
        binding.searchEditText.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().isNotEmpty()){
                    val searchText= p0.toString().trim()
                    viewModel.search(searchText)
                }else{
                    viewModel.getAllTeams()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })




        return binding.root
    }

}