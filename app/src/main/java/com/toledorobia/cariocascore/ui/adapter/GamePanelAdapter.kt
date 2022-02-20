package com.toledorobia.cariocascore.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.toledorobia.cariocascore.core.Utils
import com.toledorobia.cariocascore.databinding.ItemGamePanelGameResultsBinding
import com.toledorobia.cariocascore.databinding.ItemGamePanelRoundsResultsBinding
import com.toledorobia.cariocascore.domain.models.*
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

private const val TYPE_HEADER = 1
private const val TYPE_ITEM = 2

class GamePanelAdapter @Inject constructor(
    @ActivityContext private val context: Context,
    private val utils: Utils,
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onClickItem: ((RoundStatusModel) -> Unit)? = null
    private var rounds = listOf<RoundStatusModel>()
    private var roundsScores = listOf<GameScoreModel>()
    private var gameScores = listOf<GameScoreModel>()

    fun updateRounds(items: List<RoundStatusModel>) {
        this.rounds = items
        notifyDataSetChanged()
    }

    fun updateRoundsScores(items: List<GameScoreModel>) {
        this.roundsScores = items;
        notifyDataSetChanged()
    }

    fun updateGameScores(items: List<GameScoreModel>) {
        this.gameScores = items;
        notifyDataSetChanged()
    }

    fun setOnClickItem(onClickItem: ((RoundStatusModel) -> Unit)) {
        this.onClickItem = onClickItem
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADER
        }

        return TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEADER) {
            val binding = ItemGamePanelGameResultsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            val adapter = GamePanelGameScoresAdapter()
            val linearLayoutManager = LinearLayoutManager(context)
            binding.rvGameScores.adapter = adapter
            binding.rvGameScores.layoutManager = linearLayoutManager

            return GamePanelGameResultsViewHolder(binding, adapter)

        }
        else {
            val binding = ItemGamePanelRoundsResultsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

            val adapter = GamePanelRoundsScoresAdapter()
            val linearLayoutManager = LinearLayoutManager(context)
            binding.rvRoundsScores.adapter = adapter
            binding.rvRoundsScores.layoutManager = linearLayoutManager

            return GamePanelRoundsResultsViewHolder(binding, adapter)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GamePanelGameResultsViewHolder -> {
                holder.adapter.updateItems(gameScores)
            }
            is GamePanelRoundsResultsViewHolder -> {
                val item = rounds[position - 1]
                val roundResults = roundsScores.filter {
                    it.roundId == item.roundId
                }

                holder.binding.apply {

                    val name = utils.getStringByName(item.roundCode!!)
                    tvGamePanelRound.text = name

                    ivRoundEnd.visibility = if (item.finished) {
                        View.VISIBLE
                    }
                    else {
                        View.INVISIBLE
                    }
                }

                if (roundResults.isNotEmpty()) {
                    holder.itemView.setOnClickListener {
                        onClickItem?.invoke(item)
                    }

                    if (item.finished) {
                        holder.adapter.updateItems(roundResults)
                    }
                    else {
                        holder.adapter.updateItems(emptyList())
                    }

                }
            }
        }
    }

    override fun getItemCount(): Int {
        return rounds.size + 1
    }

    inner class GamePanelRoundsResultsViewHolder(
        val binding: ItemGamePanelRoundsResultsBinding,
        val adapter: GamePanelRoundsScoresAdapter,
    ): RecyclerView.ViewHolder(binding.root)

    inner class GamePanelGameResultsViewHolder(
        val binding: ItemGamePanelGameResultsBinding,
        val adapter: GamePanelGameScoresAdapter,
    ): RecyclerView.ViewHolder(binding.root)
}