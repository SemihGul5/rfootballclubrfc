package com.abrebo.rfootballclubrfc.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.abrebo.rfootballclubrfc.databinding.FragmentAddListBinding
import com.abrebo.rfootballclubrfc.ui.adapter.MyListAdapter
import com.abrebo.rfootballclubrfc.ui.viewmodel.MyListViewModel
import com.abrebo.rfootballclubrfc.util.PageType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddListFragment : Fragment() {
    private lateinit var binding:FragmentAddListBinding
    private lateinit var viewModel:MyListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp:MyListViewModel by viewModels()
        viewModel=temp
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=FragmentAddListBinding.inflate(inflater, container, false)

        viewModel.teamList.observe(viewLifecycleOwner){
            val adapter=MyListAdapter(requireContext(),it,viewModel,PageType.ADDLIST)
            binding.recyclerViewAddList.adapter=adapter
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