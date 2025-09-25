package com.abrebo.rfootballclubrfc.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.abrebo.rfootballclubrfc.data.model.League
import com.abrebo.rfootballclubrfc.databinding.CardLeagueBinding
import com.abrebo.rfootballclubrfc.databinding.ItemAdViewBinding
import com.abrebo.rfootballclubrfc.ui.fragment.MainFragmentDirections
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.interstitial.InterstitialAd

private const val VIEW_TYPE_CONTENT = 0
private const val VIEW_TYPE_AD = 1

class LeagueAdapter(
    private val mContext: Context,
    private val leagueList: List<League>,
    private val interstitialAd: InterstitialAd?,
    var activity: Activity
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class LeagueHolder(val binding: CardLeagueBinding) : RecyclerView.ViewHolder(binding.root)
    inner class AdViewHolder(val binding: ItemAdViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return leagueList.size + 1 // sonuna 1 reklam ekliyoruz
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == leagueList.size) VIEW_TYPE_AD else VIEW_TYPE_CONTENT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_AD) {
            val binding = ItemAdViewBinding.inflate(LayoutInflater.from(mContext), parent, false)
            AdViewHolder(binding)
        } else {
            val binding = CardLeagueBinding.inflate(LayoutInflater.from(mContext), parent, false)
            LeagueHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_AD) {
            val adHolder = holder as AdViewHolder
            val adView = AdView(mContext)
            adView.setAdSize(AdSize.MEDIUM_RECTANGLE)
            adView.adUnitId = "ca-app-pub-4667560937795938/9919793408"
            adView.loadAd(AdRequest.Builder().build())

            adHolder.binding.adContainer.removeAllViews()
            adHolder.binding.adContainer.addView(adView)

        } else {
            val league = leagueList[position]
            val leagueHolder = holder as LeagueHolder
            val binding = leagueHolder.binding

            binding.textViewLeagueName.text = league.league_name

            Glide.with(mContext)
                .load(league.logo_url)
                .into(binding.imageViewLeague)

            binding.leagueCard.setOnClickListener {
                interstitialAd?.let { ad ->
                    ad.show(activity)
                    val action = MainFragmentDirections.actionMainFragmentToLeagueFragment(league)
                    Navigation.findNavController(it).navigate(action)
                } ?: run {
                    val action = MainFragmentDirections.actionMainFragmentToLeagueFragment(league)
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }
}
