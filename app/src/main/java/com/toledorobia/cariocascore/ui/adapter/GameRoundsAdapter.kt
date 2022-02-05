package com.toledorobia.cariocascore.ui.adapter

import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toledorobia.cariocascore.databinding.ItemGameRoundsListBinding
import com.toledorobia.cariocascore.domain.models.RoundModel

class GameRoundsAdapter(
    private val onCheckedRoundListener: (round: RoundModel, checked: Boolean) -> Unit,
): RecyclerView.Adapter<GameRoundsAdapter.GameRoundsViewHolder>() {

    private var items = mutableListOf<RoundModel>()

    fun updateItems(items: List<RoundModel>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameRoundsViewHolder {
        val binding = ItemGameRoundsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameRoundsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameRoundsViewHolder, position: Int) {
        val item = items[position]

        holder.binding.apply {
            cbRound.text = Editable.Factory.getInstance().newEditable(item.name)

            cbRound.setOnCheckedChangeListener { _, checked ->
                onCheckedRoundListener(item, checked)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class GameRoundsViewHolder(val binding: ItemGameRoundsListBinding): RecyclerView.ViewHolder(binding.root)
}