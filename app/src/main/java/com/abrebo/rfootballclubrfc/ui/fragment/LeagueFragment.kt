package com.abrebo.rfootballclubrfc.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.abrebo.rfootballclubrfc.data.model.League
import com.abrebo.rfootballclubrfc.data.model.Team
import com.abrebo.rfootballclubrfc.databinding.FragmentLeagueBinding
import com.abrebo.rfootballclubrfc.ui.adapter.TeamAdapter
import com.abrebo.rfootballclubrfc.ui.viewmodel.LeagueViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeagueFragment : Fragment() {
    private lateinit var binding:FragmentLeagueBinding
    private lateinit var viewModel:LeagueViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp:LeagueViewModel by viewModels()
        viewModel=temp
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=FragmentLeagueBinding.inflate(inflater, container, false)
        val bundle=LeagueFragmentArgs.fromBundle(requireArguments())
        val league=bundle.League
        binding.materialToolbar.title=league.league_name



        viewModel.teamList.observe(viewLifecycleOwner){teams->
            val filteredTeams: List<Team> = when {
                league.league_name in listOf("Champions League", "Europa League", "Conference League") -> {
                    teams.filter { it.european_cup == league.league_name }
                }
                league.league_name == "4.5 Star -> 5 Star" -> {
                    teams.filter { it.stars >= 4.5 }
                }
                league.league_name == "4 Star -> 5 Star" -> {
                    teams.filter { it.stars >= 4 }
                }
                league.league_name == "3.5 Star -> 5 Star" -> {
                    teams.filter { it.stars >= 3.5 }
                }
                league.league_name == "3 Star -> 5 Star" -> {
                    teams.filter { it.stars >= 3 }
                }
                league.league_name == "4 Star -> 4.5 Star" -> {
                    teams.filter { it.stars in 4.0..4.5 }
                }
                league.league_name == "3.5 Star -> 4 Star" -> {
                    teams.filter { it.stars in 3.5..4.0 }
                }
                league.league_name == "3.5 Star -> 4.5 Star" -> {
                    teams.filter { it.stars in 3.5..4.5 }
                }
                league.league_name == "3 Star -> 4 Star" -> {
                    teams.filter { it.stars in 3.0..4.0 }
                }
                league.league_name == "0.5 Star -> 5 Star" -> {
                    teams.filter { it.stars in 0.5..5.0 }
                }
                league.league_name == "5 Star" -> {
                    teams.filter { it.stars==5.0 }
                }
                league.league_name == "4.5 Star" -> {
                    teams.filter { it.stars==4.5 }
                }
                league.league_name == "4 Star" -> {
                    teams.filter { it.stars==4.0 }
                }
                league.league_name == "3.5 Star" -> {
                    teams.filter { it.stars==3.5 }
                }
                else -> {
                    teams.filter { it.league_name == league.league_name && it.country_name == league.country }
                }
            }
            val adapter=TeamAdapter(requireContext(),filteredTeams)
            binding.recyclerViewLeagueFragment.adapter=adapter
        }








        return binding.root
    }

}