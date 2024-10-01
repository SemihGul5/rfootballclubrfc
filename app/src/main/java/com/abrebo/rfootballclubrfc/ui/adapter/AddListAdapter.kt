package com.abrebo.rfootballclubrfc.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abrebo.rfootballclubrfc.data.model.Team
import com.abrebo.rfootballclubrfc.databinding.CardTeamAddLlistLayoutBinding
import com.bumptech.glide.Glide

class AddListAdapter (var mContext: Context, var teamList:List<Team>): RecyclerView.Adapter<AddListAdapter.TeamHolder>() {

    inner class TeamHolder(var binding: CardTeamAddLlistLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHolder {
        val binding= CardTeamAddLlistLayoutBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return TeamHolder(binding)
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TeamHolder, position: Int) {
        val team=teamList.get(position)
        val binding=holder.binding

        Glide.with(mContext)
            .load(team.team_image_url)
            .into(binding.imageViewTeamLogo)
        binding.teamNameText.text=team.team_name
        binding.teamInfoText.text="${team.overall} - ${team.league_name}"
        binding.teamStarText.text=team.stars.toString()

        binding.addTeamToMyList.setOnClickListener {

        }

    }
}