package com.toledorobia.cariocascore.ui.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.toledorobia.cariocascore.R
import com.toledorobia.cariocascore.databinding.ItemGameListBinding
import com.toledorobia.cariocascore.domain.models.GameDashboardModel

class GameAdapter(
    private val onClickItem: (game: GameDashboardModel) -> Unit,
): RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private var items = listOf<GameDashboardModel>()

    fun updateItems(items: List<GameDashboardModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemGameListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.binding.apply {
            val item = items[position]
            tvGame.text = item.name
            tvPlayers.text = "${item.players.toString()} Players"
            tvRounds.text = "${item.rounds.toString()} Rounds"

            tvFinished.visibility = if (item.finished!!) {
                View.VISIBLE
            }
            else{
                View.GONE
            }

            tvPending.visibility = if (!item.finished!!) {
                View.VISIBLE
            }
            else{
                View.GONE
            }

            holder.itemView.setOnClickListener {
                onClickItem.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class GameViewHolder(val binding: ItemGameListBinding): RecyclerView.ViewHolder(binding.root)
}