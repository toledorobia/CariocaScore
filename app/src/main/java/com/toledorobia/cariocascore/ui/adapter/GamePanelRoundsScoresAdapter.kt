package com.toledorobia.cariocascore.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toledorobia.cariocascore.databinding.ItemGamePanelRoundsScoresAdapterBinding
import com.toledorobia.cariocascore.domain.models.GameScoreModel

class GamePanelRoundsScoresAdapter(): RecyclerView.Adapter<GamePanelRoundsScoresAdapter.GamePanelRoundsScoresViewHolder>() {
    private var items = listOf<GameScoreModel>()

    fun updateItems(items: List<GameScoreModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamePanelRoundsScoresViewHolder {
        val binding = ItemGamePanelRoundsScoresAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GamePanelRoundsScoresViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GamePanelRoundsScoresViewHolder, position: Int) {
        holder.binding.apply {
            val item = items[position]
            tvGamePanelResultPlayerName.text = item.playerName
            ivWinner.visibility = if (item.winner) {
                View.VISIBLE
            }
            else {
                View.INVISIBLE
            }

            when (item.score) {
                null -> {
                    tvScore.text = "--"
                }
                else -> {
                    tvScore.text = item.score.toString()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class GamePanelRoundsScoresViewHolder(val binding: ItemGamePanelRoundsScoresAdapterBinding): RecyclerView.ViewHolder(binding.root)
}