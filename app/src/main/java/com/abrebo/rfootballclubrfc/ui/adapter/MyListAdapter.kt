package com.abrebo.rfootballclubrfc.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abrebo.rfootballclubrfc.R
import com.abrebo.rfootballclubrfc.data.model.Team
import com.abrebo.rfootballclubrfc.databinding.CardTeamListLayoutBinding
import com.abrebo.rfootballclubrfc.ui.viewmodel.MyListViewModel
import com.abrebo.rfootballclubrfc.util.PageType
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class MyListAdapter (var mContext: Context,
                     var teamList:List<Team>,
                     var viewModel: MyListViewModel,
                     var page:PageType): RecyclerView.Adapter<MyListAdapter.TeamHolder>() {

    inner class TeamHolder(var binding: CardTeamListLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHolder {
        val binding= CardTeamListLayoutBinding.inflate(LayoutInflater.from(mContext), parent, false)
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
        teamProcessImage(binding)



        binding.teamProcess.setOnClickListener {
            if (page==PageType.ADDLIST){
                viewModel.addTeam(team)
                Snackbar.make(it,"${team.team_name} ${mContext.getString(R.string.added_to_list)}",Snackbar.LENGTH_SHORT).show()
            }else{
                viewModel.deleteTeam(team)
                Snackbar.make(it,"${team.team_name} ${mContext.getString(R.string.removed_from_list)}",Snackbar.LENGTH_SHORT).show()
            }
        }

    }

    private fun teamProcessImage(binding: CardTeamListLayoutBinding) {
        if (page==PageType.ADDLIST){
            binding.teamProcess.setImageResource(R.drawable.baseline_add_grey)
        }else{
            binding.teamProcess.setImageResource(R.drawable.baseline_delete_24)
        }
    }
}