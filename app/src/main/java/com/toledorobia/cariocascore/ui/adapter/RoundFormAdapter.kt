package com.toledorobia.cariocascore.ui.adapter

import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.toledorobia.cariocascore.databinding.ItemRoundFormAdapterBinding
import com.toledorobia.cariocascore.domain.models.GameScoreModel

class RoundFormAdapter(
    private val onSetScoreListener: (id: Int?, score: Int?) -> Unit,
): RecyclerView.Adapter<RoundFormAdapter.RoundFormViewHolder>() {

    private var items = mutableListOf<GameScoreModel>()

    fun updateItems(items: List<GameScoreModel>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoundFormViewHolder {
        val binding = ItemRoundFormAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoundFormViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoundFormViewHolder, position: Int) {
        val item = items[position]

        holder.binding.apply {
            tvRoundPlayerName.text = item.playerName

            if (holder.textWatcher != null) {
                etScore.removeTextChangedListener(holder.textWatcher)
            }

            holder.textWatcher = etScore.addTextChangedListener {
                var score: Int? = null

                if (it.toString().isNotBlank()) {
                    score = it.toString().toInt()
                }

                onSetScoreListener.invoke(item.id, score)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class RoundFormViewHolder(val binding: ItemRoundFormAdapterBinding): RecyclerView.ViewHolder(binding.root) {
        var textWatcher: TextWatcher? = null
    }
}