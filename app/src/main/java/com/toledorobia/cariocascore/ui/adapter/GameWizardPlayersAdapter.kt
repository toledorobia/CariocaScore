package com.toledorobia.cariocascore.ui.adapter

import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toledorobia.cariocascore.databinding.ItemGameWizardPlayersAdapterBinding
import com.toledorobia.cariocascore.domain.models.PlayerModel

class GameWizardPlayersAdapter(
    private val onCheckedPlayerListener: (player: PlayerModel, checked: Boolean) -> Unit,
): RecyclerView.Adapter<GameWizardPlayersAdapter.GameWizardPlayersViewHolder>() {

    private var items = mutableListOf<PlayerModel>()

    fun updateItems(items: List<PlayerModel>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameWizardPlayersViewHolder {
        val binding = ItemGameWizardPlayersAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameWizardPlayersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameWizardPlayersViewHolder, position: Int) {
        val item = items[position]

        holder.binding.apply {
            cbPlayer.text = Editable.Factory.getInstance().newEditable(item.name)

            cbPlayer.setOnCheckedChangeListener { _, checked ->
                onCheckedPlayerListener(item, checked)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class GameWizardPlayersViewHolder(val binding: ItemGameWizardPlayersAdapterBinding): RecyclerView.ViewHolder(binding.root)
}