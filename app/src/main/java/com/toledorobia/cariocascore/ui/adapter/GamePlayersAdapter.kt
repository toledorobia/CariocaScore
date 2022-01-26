package com.toledorobia.cariocascore.ui.adapter

import android.text.Editable
import android.text.SpanWatcher
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.toledorobia.cariocascore.databinding.ItemAddGamePlayersListBinding
import com.toledorobia.cariocascore.databinding.ItemGamePlayersListBinding

private const val NORMAL_VIEW_TYPE = 1
private const val FOOTER_VIEW_TYPE = 2

class GamePlayersAdapter(
    private val onAddPlayerClickListener: () -> Unit,
    private val onRemovePlayerClickListener: (position: Int) -> Unit,
    private val onChangePlayerListener: (position: Int, name: String) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = mutableListOf<String>()

    fun updateItems(items: MutableList<String>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (items.isEmpty() || position == items.size) {
            return FOOTER_VIEW_TYPE
        }

        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == FOOTER_VIEW_TYPE) {
            val binding = ItemAddGamePlayersListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AddGamePlayersViewHolder(binding)
        }

        val binding = ItemGamePlayersListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GamePlayersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is GamePlayersViewHolder -> {
                val item = items[position]
                holder.binding.apply {

                    etGamePlayerName.apply {
                        removeTextChangedListener(holder.watcher)
                        holder.watcher = addTextChangedListener {
                            onChangePlayerListener(position, it.toString())
                        }

                        text = Editable.Factory.getInstance().newEditable(item)
                    }

                    btGamePlayerAdd.setOnClickListener {
                        onRemovePlayerClickListener(position)
                    }
                }
            }
            is AddGamePlayersViewHolder -> {
                holder.binding.btGamePlayerAdd.setOnClickListener {
                    onAddPlayerClickListener()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size + 1
    }

    inner class GamePlayersViewHolder(val binding: ItemGamePlayersListBinding): RecyclerView.ViewHolder(binding.root) {
        var watcher: TextWatcher? = null
    }


    inner class AddGamePlayersViewHolder(val binding: ItemAddGamePlayersListBinding): RecyclerView.ViewHolder(binding.root)
}