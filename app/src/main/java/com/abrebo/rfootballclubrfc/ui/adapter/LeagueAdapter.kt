package com.abrebo.rfootballclubrfc.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abrebo.rfootballclubrfc.data.model.League
import com.abrebo.rfootballclubrfc.databinding.CardLeagueBinding
import com.abrebo.rfootballclubrfc.databinding.FragmentAddListBinding
import com.bumptech.glide.Glide


class LeagueAdapter(var mContext:Context, var leagueList: List<League>):RecyclerView.Adapter<LeagueAdapter.LeagueHolder>() {

    inner class LeagueHolder(var binding: CardLeagueBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueHolder {
        val binding= CardLeagueBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return LeagueHolder(binding)
    }

    override fun getItemCount(): Int {
        return leagueList.size
    }

    override fun onBindViewHolder(holder: LeagueHolder, position: Int) {
        val league=leagueList.get(position)
        val binding=holder.binding
        binding.textViewLeagueName.text=league.league_name

        Glide.with(mContext)
            .load(league.logo_url)
            .into(binding.imageViewLeague)

        binding.leagueCard.setOnClickListener {

        }
    }
}