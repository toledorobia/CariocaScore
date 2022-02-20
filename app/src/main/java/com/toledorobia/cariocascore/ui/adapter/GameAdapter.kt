package com.toledorobia.cariocascore.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toledorobia.cariocascore.R
import com.toledorobia.cariocascore.databinding.ItemGameAdapterBinding
import com.toledorobia.cariocascore.domain.models.GameStatusModel
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class GameAdapter @Inject constructor(
    @ActivityContext private val context: Context,
): RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private var onClickItem: ((GameStatusModel) -> Unit)? = null
    private var items = listOf<GameStatusModel>()

    fun updateItems(items: List<GameStatusModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun setOnClickItem(onClickItem: ((GameStatusModel) -> Unit)) {
        this.onClickItem = onClickItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemGameAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.binding.apply {
            val item = items[position]
            tvGame.text = item.name

            context?.resources?.apply {
                tvPlayers.text = getQuantityString(R.plurals.number_players, item.players ?: 0, item.players ?: 0)
                tvRounds.text = getQuantityString(R.plurals.number_rounds, item.rounds ?: 0, item.rounds ?: 0)
            }

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

            tvWinner.apply {
                if (item.winner != null) {
                    visibility = View.VISIBLE
                    text = item.winner
                }
                else {
                    visibility = View.GONE
                }
            }

            holder.itemView.setOnClickListener {
                onClickItem?.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class GameViewHolder(val binding: ItemGameAdapterBinding): RecyclerView.ViewHolder(binding.root)
}