package com.toledorobia.cariocascore.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toledorobia.cariocascore.databinding.ItemGamePanelGameScoresAdapterBinding
import com.toledorobia.cariocascore.domain.models.GameScoreModel

class GamePanelGameScoresAdapter(): RecyclerView.Adapter<GamePanelGameScoresAdapter.GamePanelGameScoresViewHolder>() {
    private var items = listOf<GameScoreModel>()

    fun updateItems(items: List<GameScoreModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamePanelGameScoresViewHolder {
        val binding = ItemGamePanelGameScoresAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GamePanelGameScoresViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GamePanelGameScoresViewHolder, position: Int) {
        holder.binding.apply {
            val item = items[position]
            tvGameScoresPlayerName.text = item.playerName
            tvGameScoresPlayerScore.text = if (item.score == null) {
                "--"
            } else {
                item.score.toString()
            }

            ivGameScoresPlayerWinner.visibility = if (item.winner) {
                View.VISIBLE
            }
            else {
                View.INVISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class GamePanelGameScoresViewHolder(val binding: ItemGamePanelGameScoresAdapterBinding): RecyclerView.ViewHolder(binding.root)
}