package com.toledorobia.cariocascore.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toledorobia.cariocascore.R
import com.toledorobia.cariocascore.databinding.ItemPlayersAdapterBinding
import com.toledorobia.cariocascore.domain.models.PlayerStatsModel

class PlayersAdapter(
    private val context: Context?,
    private val onClickItem: (player: PlayerStatsModel) -> Unit,
): RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder>() {

    private var items = listOf<PlayerStatsModel>()

    fun updateItems(items: List<PlayerStatsModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val binding = ItemPlayersAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        holder.binding.apply {
            val item = items[position]
            tvPlayerName.text = item.playerName

            context?.resources?.apply {
                tvPlayerMatches.text = getQuantityString(R.plurals.number_matches, item.matches ?: 0, item.matches ?: 0)
                tvPlayerWins.text = getQuantityString(R.plurals.number_wins, item.wins ?: 0, item.matches ?: 0)
                tvPlayerLosses.text = getQuantityString(R.plurals.number_losses, item.losses ?: 0, item.matches ?: 0)
            }

            holder.itemView.setOnClickListener {
                onClickItem.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class PlayersViewHolder(val binding: ItemPlayersAdapterBinding): RecyclerView.ViewHolder(binding.root)
}