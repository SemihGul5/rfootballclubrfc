package com.abrebo.rfootballclubrfc.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.abrebo.rfootballclubrfc.R
import com.abrebo.rfootballclubrfc.databinding.FragmentTurnuvaOlusturBinding
import com.abrebo.rfootballclubrfc.util.PageType

class TurnuvaOlusturFragment : Fragment() {
    private lateinit var binding:FragmentTurnuvaOlusturBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentTurnuvaOlusturBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.player1TeamSelect.setOnClickListener {
            val navd=TurnuvaOlusturFragmentDirections.actionTurnuvaOlusturFragmentToAddListFragment(PageType.P1_ADD_LIST)
            Navigation.findNavController(it).navigate(navd)
        }
        binding.player1TeamSelect.setOnClickListener {
            val navd=TurnuvaOlusturFragmentDirections.actionTurnuvaOlusturFragmentToAddListFragment(PageType.P2_ADD_LIST)
            Navigation.findNavController(it).navigate(navd)
        }

    }

}