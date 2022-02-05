package com.toledorobia.cariocascore.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toledorobia.cariocascore.databinding.ItemPlayersListBinding
import com.toledorobia.cariocascore.domain.models.PlayerModel

class PlayersAdapter(
    private val context: Context?,
    private val onEditPlayer: (player: PlayerModel) -> Unit,
    private val onDeletePlayer: (player: PlayerModel) -> Unit,
    private val onStatsPlayer: (player: PlayerModel) -> Unit,
    private val onClickItem: (player: PlayerModel) -> Unit,
): RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder>() {

    private var items = listOf<PlayerModel>()

    fun updateItems(items: List<PlayerModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val binding = ItemPlayersListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {


        holder.binding.apply {
            val item = items[position]
            tvPlayerName.text = item.name

            holder.itemView.setOnClickListener {
                onClickItem.invoke(item)
            }

//            holder.binding.root.setOnClickListener {
//                onClickItem.invoke(item)
//            }

//            val popupMenu = PopupMenu(context, ibPlayerMenu)
//            popupMenu.menuInflater.inflate(R.menu.player_menu, popupMenu.menu)
//            popupMenu.setOnMenuItemClickListener {
//                when (it.itemId) {
//                    R.id.menu_player_edit -> onEditPlayer(item)
//                    R.id.menu_player_delete -> onDeletePlayer(item)
//                    R.id.menu_player_stats -> onStatsPlayer(item)
//                }
//                true
//            }
//
//            ibPlayerMenu.setOnClickListener {
//                popupMenu.show()
//            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class PlayersViewHolder(val binding: ItemPlayersListBinding): RecyclerView.ViewHolder(binding.root)
}