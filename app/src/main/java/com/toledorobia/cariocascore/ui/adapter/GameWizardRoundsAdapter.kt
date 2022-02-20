package com.toledorobia.cariocascore.ui.adapter

import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toledorobia.cariocascore.core.Utils
import com.toledorobia.cariocascore.databinding.ItemGameWizardRoundsAdapterBinding
import com.toledorobia.cariocascore.domain.models.RoundModel
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class GameWizardRoundsAdapter @Inject constructor(
    private val utils: Utils,
) : RecyclerView.Adapter<GameWizardRoundsAdapter.GameWizardRoundsViewHolder>() {

    private var onCheckedRoundListener: ((RoundModel, Boolean) -> Unit)? = null
    private var items = mutableListOf<RoundModel>()

    fun updateItems(items: List<RoundModel>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    fun setOnCheckedRoundLister(onCheckedRoundListener: ((RoundModel, Boolean) -> Unit)) {
        this.onCheckedRoundListener = onCheckedRoundListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameWizardRoundsViewHolder {
        val binding = ItemGameWizardRoundsAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameWizardRoundsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameWizardRoundsViewHolder, position: Int) {
        val item = items[position]

        holder.binding.apply {
            val name = utils.getStringByName(item.code)
            cbRound.text = Editable.Factory.getInstance().newEditable(name)

            cbRound.setOnCheckedChangeListener { _, checked ->
                onCheckedRoundListener?.invoke(item, checked)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class GameWizardRoundsViewHolder(val binding: ItemGameWizardRoundsAdapterBinding): RecyclerView.ViewHolder(binding.root)
}