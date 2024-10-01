package com.abrebo.rfootballclubrfc.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.abrebo.rfootballclubrfc.R
import com.abrebo.rfootballclubrfc.data.model.Team
import com.abrebo.rfootballclubrfc.databinding.FragmentLeagueBinding
import com.abrebo.rfootballclubrfc.ui.adapter.RandomTeamAdapter
import com.abrebo.rfootballclubrfc.ui.adapter.TeamAdapter
import com.abrebo.rfootballclubrfc.ui.viewmodel.LeagueViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeagueFragment : Fragment() {
    private lateinit var binding:FragmentLeagueBinding
    private lateinit var viewModel:LeagueViewModel
    private lateinit var filteredTeams:List<Team>
    private lateinit var randomTeamAdapter: RandomTeamAdapter
    private var randomTeams=ArrayList<Team>()
    private var homeTeam:Team?=null
    private var awayTeam:Team?=null
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
        Glide.with(requireContext()).load(league.flag_url).into(binding.imageViewLeagueFlag)
        filteredTeams=ArrayList<Team>()

        viewModel.teamList.observe(viewLifecycleOwner){teams->
            filteredTeams = when {
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


        binding.imageViewBack.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_leagueFragment_to_mainFragment)
        }


        randomTeamAdapter=RandomTeamAdapter(requireContext(),randomTeams)
        binding.recyclerViewPredict.adapter=randomTeamAdapter


        binding.buttonHome.setOnClickListener {
            addRandomTeam(true)
        }
        binding.buttonAway.setOnClickListener {
            addRandomTeam(false)
        }



        return binding.root
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun addRandomTeam(isHomeTeam: Boolean) {
        randomTeams.clear()

        if (filteredTeams.size > 2) {
            if (isHomeTeam) {
                homeTeam = viewModel.getRandomTeam(filteredTeams as ArrayList<Team>, homeTeam, awayTeam)
            } else {
                awayTeam = viewModel.getRandomTeam(filteredTeams as ArrayList<Team>, homeTeam, awayTeam)
            }

            homeTeam?.let { randomTeams.add(it) }
            awayTeam?.let { randomTeams.add(it) }
            randomTeamAdapter.notifyDataSetChanged()
        }else{
            Snackbar.make(requireView(), "Zaten 1 veya 2 takım var, rastgele takım seçmeye gerek yok !", Snackbar.LENGTH_SHORT).show()
        }
    }

}