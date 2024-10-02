package com.abrebo.rfootballclubrfc.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.abrebo.rfootballclubrfc.data.model.League
import com.abrebo.rfootballclubrfc.databinding.CardLeagueBinding
import com.abrebo.rfootballclubrfc.ui.fragment.MainFragmentDirections
import com.bumptech.glide.Glide
import com.google.android.gms.ads.interstitial.InterstitialAd

class LeagueAdapter(
    private val mContext: Context,
    private val leagueList: List<League>,
    private val interstitialAd: InterstitialAd?,
    var activity: Activity) : RecyclerView.Adapter<LeagueAdapter.LeagueHolder>() {

    inner class LeagueHolder(var binding: CardLeagueBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueHolder {
        val binding = CardLeagueBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return LeagueHolder(binding)
    }

    override fun getItemCount(): Int {
        return leagueList.size
    }

    override fun onBindViewHolder(holder: LeagueHolder, position: Int) {
        val league = leagueList[position]
        val binding = holder.binding
        binding.textViewLeagueName.text = league.league_name

        Glide.with(mContext)
            .load(league.logo_url)
            .into(binding.imageViewLeague)

        binding.leagueCard.setOnClickListener {
            // Interstitial Ad gösterimi
            interstitialAd?.let { ad ->
                ad.show(activity)
                val action = MainFragmentDirections.actionMainFragmentToLeagueFragment(league)
                Navigation.findNavController(it).navigate(action)
            }?:run {
                val action = MainFragmentDirections.actionMainFragmentToLeagueFragment(league)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}
